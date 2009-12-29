package com.jetbrains.python.debugger;

import com.intellij.xdebugger.frame.XExecutionStack;
import com.intellij.xdebugger.frame.XStackFrame;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class PyExecutionStack extends XExecutionStack {

  private final PyDebugProcess myDebugProcess;
  private final PyThreadInfo myThreadInfo;

  public PyExecutionStack(@NotNull final PyDebugProcess debugProcess, @NotNull final PyThreadInfo threadInfo) {
    super(threadInfo.getName());
    myDebugProcess = debugProcess;
    myThreadInfo = threadInfo;
  }

  @Override
  public XStackFrame getTopFrame() {
    final List<PyStackFrameInfo> frames = myThreadInfo.getFrames();
    return frames != null ? convert(myDebugProcess, frames.get(0)) : null;
  }

  @Override
  public void computeStackFrames(int firstFrameIndex, XStackFrameContainer container) {
    if (myThreadInfo.getState() != PyThreadInfo.State.SUSPENDED) {
      container.errorOccured("Frames not available in non-suspended state");
      return;
    }

    final List<PyStackFrameInfo> frames = myThreadInfo.getFrames();
    if (frames != null && firstFrameIndex <= frames.size()) {
      final List<PyStackFrame> xFrames = new LinkedList<PyStackFrame>();
      for (int i = firstFrameIndex; i < frames.size(); i++) {
        xFrames.add(convert(myDebugProcess, frames.get(i)));
      }
      container.addStackFrames(xFrames, true);
    }
    else {
      container.addStackFrames(Collections.<XStackFrame>emptyList(), true);
    }
  }

  private static PyStackFrame convert(final PyDebugProcess debugProcess, final PyStackFrameInfo frameInfo) {
    return new PyStackFrame(debugProcess, frameInfo);
  }

}
