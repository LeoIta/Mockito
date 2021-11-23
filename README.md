<h1>Mockito project</h1>
<hr>
<p>Reference to: <a href = 'https://www.udemy.com/course/mockito3'>Mockito: Next-Level Java Unit Testing</a></p>
<h2>Matchers Rules</h2>
<hr>
<ul>
<li>Use any() for Objects. For primitives, use anyDouble(), anyBoolean() etc.</li>
<li>
Use eq() to mix matchers and concrete values:
<br><i>method(any(), eq(400.0))</i>
</li>
<li>
For nullable Strings, use any() instead of anyString() as the second one doesn't cover the null case.
</li>
</ul>