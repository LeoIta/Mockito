<h1>Mockito project</h1><hr>
<p>Reference to: <a href = 'https://www.udemy.com/course/mockito3'>Mockito: Next-Level Java Unit Testing</a></p>
<h2>Matchers Rules</h2><hr>
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
<h2>Spy vs Mock</h2><hr>
<h4>MOCK:</h4> 
dummy object with no real logic and always returns the default value
<br><i>when(mock.method()).thenReturn()</i>
<h4>SPY:</h4>
partial mock, a real object with real logic that we can modify
<br><i>doReturn().when(spy.method())</i>
<h2>Mock throw exception</h2><hr>
<h4>Void methods:</h4>
<i>doThrow(Exception.class).when(MockObject).mockMethod();</i>
<h4>Other methods:</h4>
<i>when(MockObject.MockMethod()).thenThrow(Exception.class);</i>