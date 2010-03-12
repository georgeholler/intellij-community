# fail

<error descr="can't assign to function call">int(1)</error> = 1

<error descr="can't assign to literal">12</error> = 1

<error descr="can't assign to operator">1 + 21</error> = 12

<error descr="can't assign to ()">()</error> = 123
<error descr="can't assign to []">[]</error> = 1
[<error descr="can't assign to literal">1</error>] = 1
<error descr="can't assign to literal">{}</error> = 1
<error descr="can't assign to literal">{1, 2, 3}</error> = 1

(<error descr="can't assign to literal">1</error>,(<error descr="can't assign to literal">2</error>, <error descr="can't assign to literal">3</error>)) = 3,(4,5)
del <error descr="can't delete literal">1</error>
del <error descr="can't delete function call">int()</error>

for <error descr="can't assign to literal">1</error> in []:
  pass

for (<error descr="can't assign to literal">1</error>,(<error descr="can't assign to literal">2</error>,)) in [12]:
  pass

<error descr="augmented assign to dict comprehension not possible">{ x: y for y, x in ((1, 2), (3, 4)) }</error> += 5
<error descr="can't assign to set comprehension">{ x for x in (1, 2) }</error> = 5

# ok

for (a,b) in []:
  pass

a[1] = 1

[a, b] = 1, 2

[foo()[1], (c, d.e)] = 1, 2, 3


z = None
