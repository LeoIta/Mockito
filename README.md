<h1>Mockito project</h1>
<p>Reference to: <a href = 'https://www.udemy.com/course/mockito3'>Mockito: Next-Level Java Unit Testing</a></p>
<h2>Matchers Rules</h2>
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
<h2>Spy vs Mock</h2>
<h4>MOCK:</h4> 
dummy object with no real logic and always returns the default value
<br><i>when(mock.method()).thenReturn()</i>
<h4>SPY:</h4>
partial mock, a real object with real logic that we can modify
<br><i>doReturn().when(spy.method())</i>
<h2>Mockito Throw Exception</h2>
<h4>Void methods:</h4>
<i>doThrow(Exception.class).when(MockObject).mockMethod();</i>
<h4>Other methods:</h4>
<i>when(MockObject.MockMethod()).thenThrow(Exception.class);</i>
<h2>Mockito Argument Captors</h2>
ArgumentCaptor&#10092;T&#10093; object can be used when we want to capture and use an argument later in the test. Used methods are:
<li><i>argumentCaptor.capture()</i> to capture the value(s) and then we use</li>
<li><i>argumentCaptor.getValue()</i> to get the last captured value</li>
<li><i>argumentCaptor.getAllValues()</i> to get all the captured values in a list</li>
<h2>Mockito Annotations</h2>
In order to use Mockito annotations, we need annotate the class with:
<li>@RunWith(MockitoExtension.class) - if you use JUnit4</li>
<li>@ExtendWith(MockitoExtension.class) - if you use JUnit5</li>
<h3>annotations @</h3>
@Mock instead of mock(object.class)<br>
@Spy instead of spy(object.class)<br>
@InjectMocks instead of initialize the object that has mock/spy dependencies<br>
@Captor instead of ArgumentCaptor.forClass(object.class)
<h2>From Mockito to Mockito BDD</h2>
when...thenReturn => given...willReturn<br>
verify(mockObject,times(int)).method() => then(mockObject).should(times(int)).method()
<h2>Mock static method</h2>
In order to be able to mock static method (possible since Mockito 3.4.0), we should change the dependency in the pom.xml file<br>
from: mockito-core<br>
to: mockito-inline