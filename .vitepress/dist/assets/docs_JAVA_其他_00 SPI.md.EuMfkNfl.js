import{_ as a,c as n,a2 as p,o as e}from"./chunks/framework.D7kq4b6j.js";const v=JSON.parse('{"title":"","description":"","frontmatter":{},"headers":[],"relativePath":"docs/JAVA/其他/00 SPI.md","filePath":"docs/JAVA/其他/00 SPI.md","lastUpdated":1723799597000}'),l={name:"docs/JAVA/其他/00 SPI.md"};function i(r,s,c,t,o,d){return e(),n("div",null,s[0]||(s[0]=[p(`<h2 id="什么是spi机制" tabindex="-1">什么是SPI机制 <a class="header-anchor" href="#什么是spi机制" aria-label="Permalink to &quot;什么是SPI机制&quot;">​</a></h2><p>SPI（Service Provider Interface），是JDK内置的一种 服务提供发现机制，可以用来启用框架扩展和替换组件，主要是被框架的开发人员使用，比如java.sql.Driver接口，其他不同厂商可以针对同一接口做出不同的实现，MySQL和PostgreSQL都有不同的实现提供给用户，而Java的SPI机制可以为某个接口寻找服务实现。Java中SPI机制主要思想是将装配的控制权移到程序之外，在模块化设计中这个机制尤其重要，其核心思想就是 <strong>解耦</strong>。 SPI整体机制图如下： ![[attachments/Pasted image 20240716163843.png]]</p><p>当服务的提供者提供了一种接口的实现之后，需要在classpath下的<code>META-INF/services/</code>目录里创建一个以服务接口命名的文件，这个文件里的内容就是这个接口的具体的实现类。当其他的程序需要这个服务的时候，就可以通过查找这个jar包（一般都是以jar包做依赖）的<code>META-INF/services/</code>中的配置文件，配置文件中有接口的具体实现类名，可以根据这个类名进行加载实例化，就可以使用该服务了。JDK中查找服务的实现的工具类是：<code>java.util.ServiceLoader</code>。</p><h2 id="spi机制的简单示例" tabindex="-1">SPI机制的简单示例 <a class="header-anchor" href="#spi机制的简单示例" aria-label="Permalink to &quot;SPI机制的简单示例&quot;">​</a></h2><p>我们现在需要使用一个内容搜索接口，搜索的实现可能是基于文件系统的搜索，也可能是基于数据库的搜索。</p><ul><li>先定义好接口</li></ul><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>public interface Search {</span></span>
<span class="line"><span>    public List&lt;String&gt; searchDoc(String keyword);   </span></span>
<span class="line"><span>}</span></span></code></pre></div><ul><li>文件搜索实现</li></ul><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>public class FileSearch implements Search{</span></span>
<span class="line"><span>    @Override</span></span>
<span class="line"><span>    public List&lt;String&gt; searchDoc(String keyword) {</span></span>
<span class="line"><span>        System.out.println(&quot;文件搜索 &quot;+keyword);</span></span>
<span class="line"><span>        return null;</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>}</span></span></code></pre></div><ul><li>数据库搜索实现</li></ul><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>public class DatabaseSearch implements Search{</span></span>
<span class="line"><span>    @Override</span></span>
<span class="line"><span>    public List&lt;String&gt; searchDoc(String keyword) {</span></span>
<span class="line"><span>        System.out.println(&quot;数据搜索 &quot;+keyword);</span></span>
<span class="line"><span>        return null;</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>}</span></span></code></pre></div><ul><li>resources 接下来可以在resources下新建META-INF/services/目录，然后新建接口全限定名的文件：<code>org.example.Search</code>，里面加上我们需要用到的实现类</li></ul><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>org.example.DatabaseSearch  </span></span>
<span class="line"><span>org.example.FileSearch</span></span></code></pre></div><ul><li>测试方法</li></ul><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>public class TestCase {</span></span>
<span class="line"><span>    public static void main(String[] args) {</span></span>
<span class="line"><span>        ServiceLoader&lt;Search&gt; s = ServiceLoader.load(Search.class);</span></span>
<span class="line"><span>        Iterator&lt;Search&gt; iterator = s.iterator();</span></span>
<span class="line"><span>        while (iterator.hasNext()) {</span></span>
<span class="line"><span>           Search search =  iterator.next();</span></span>
<span class="line"><span>           search.searchDoc(&quot;hello world&quot;);</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>}</span></span></code></pre></div><p>可以看到输出结果： ![[attachments/Pasted image 20240716174056.png]]</p><p>如果在org.example.Search文件里写上两个实现类，那最后的输出结果就是两行了。</p><p>这就是因为<code>ServiceLoader.load(Search.class)</code>在加载某接口时，会去<code>META-INF/services</code>下找接口的全限定名文件，再根据里面的内容加载相应的实现类。</p><p>这就是spi的思想，接口的实现由provider实现，provider只用在提交的jar包里的<code>META-INF/services</code>下根据平台定义的接口新建文件，并添加进相应的实现类内容就好。</p><h2 id="spi机制的广泛应用" tabindex="-1">SPI机制的广泛应用 <a class="header-anchor" href="#spi机制的广泛应用" aria-label="Permalink to &quot;SPI机制的广泛应用&quot;">​</a></h2><h3 id="spi机制-jdbc-drivermanager" tabindex="-1">SPI机制 - JDBC DriverManager <a class="header-anchor" href="#spi机制-jdbc-drivermanager" aria-label="Permalink to &quot;SPI机制 - JDBC DriverManager&quot;">​</a></h3><h4 id="jdbc接口定义" tabindex="-1">JDBC接口定义 <a class="header-anchor" href="#jdbc接口定义" aria-label="Permalink to &quot;JDBC接口定义&quot;">​</a></h4><p>首先在java中定义了接口<code>java.sql.Driver</code>，并没有具体的实现，具体的实现都是由不同厂商来提供的。</p><h4 id="mysql实现" tabindex="-1">mysql实现 <a class="header-anchor" href="#mysql实现" aria-label="Permalink to &quot;mysql实现&quot;">​</a></h4><p>在mysql的jar包<code>mysql-connector-java-6.0.6.jar</code>中，可以找到<code>META-INF/services</code>目录，该目录下会有一个名字为<code>java.sql.Driver</code>的文件，文件内容是<code>com.mysql.cj.jdbc.Driver</code>，这里面的内容就是针对Java中定义的接口的实现。</p><h4 id="postgresql实现" tabindex="-1">postgresql实现 <a class="header-anchor" href="#postgresql实现" aria-label="Permalink to &quot;postgresql实现&quot;">​</a></h4><p>同样在postgresql的jar包<code>postgresql-42.0.0.jar</code>中，也可以找到同样的配置文件，文件内容是<code>org.postgresql.Driver</code>，这是postgresql对Java的<code>java.sql.Driver</code>的实现。</p><h4 id="使用方法" tabindex="-1"><a href="#使用方法">#</a> 使用方法 <a class="header-anchor" href="#使用方法" aria-label="Permalink to &quot;[#](#使用方法) 使用方法&quot;">​</a></h4><p>上面说了，现在使用SPI扩展来加载具体的驱动，我们在Java中写连接数据库的代码的时候，不需要再使用<code>Class.forName(&quot;com.mysql.jdbc.Driver&quot;)</code>来加载驱动了，而是直接使用如下代码：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>String url = &quot;jdbc:xxxx://xxxx:xxxx/xxxx&quot;;</span></span>
<span class="line"><span>Connection conn = DriverManager.getConnection(url,username,password);</span></span>
<span class="line"><span>.....</span></span></code></pre></div><p>这里并没有涉及到spi的使用，接着看下面的解析。</p><h4 id="源码实现" tabindex="-1">源码实现 <a class="header-anchor" href="#源码实现" aria-label="Permalink to &quot;源码实现&quot;">​</a></h4><p>上面的使用方法，就是我们普通的连接数据库的代码，并没有涉及到SPI的东西，但是有一点我们可以确定的是，我们没有写有关具体驱动的硬编码<code>Class.forName(&quot;com.mysql.jdbc.Driver&quot;)</code>！</p><p>上面的代码可以直接获取数据库连接进行操作，但是跟SPI有啥关系呢？上面代码没有加载驱动的代码，我们怎么去确定使用哪个数据库连接的驱动呢？这里就涉及到使用Java的SPI扩展机制来查找相关驱动的东西了，关于驱动的查找其实都在 <code>DriverManager</code> 中，<code>DriverManager</code> 是Java中的实现，用来获取数据库连接，在 <code>DriverManager</code> 中有一个静态代码块如下：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>static {</span></span>
<span class="line"><span>    loadInitialDrivers();</span></span>
<span class="line"><span>    println(&quot;JDBC DriverManager initialized&quot;);</span></span>
<span class="line"><span>}</span></span></code></pre></div><p>可以看到是加载实例化驱动的，接着看loadInitialDrivers方法：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>private static void loadInitialDrivers() {</span></span>
<span class="line"><span>    String drivers;</span></span>
<span class="line"><span>    try {</span></span>
<span class="line"><span>        drivers = AccessController.doPrivileged(new PrivilegedAction&lt;String&gt;() {</span></span>
<span class="line"><span>            public String run() {</span></span>
<span class="line"><span>                return System.getProperty(&quot;jdbc.drivers&quot;);</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>        });</span></span>
<span class="line"><span>    } catch (Exception ex) {</span></span>
<span class="line"><span>        drivers = null;</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    AccessController.doPrivileged(new PrivilegedAction&lt;Void&gt;() {</span></span>
<span class="line"><span>        public Void run() {</span></span>
<span class="line"><span>			//使用SPI的ServiceLoader来加载接口的实现</span></span>
<span class="line"><span>            ServiceLoader&lt;Driver&gt; loadedDrivers = ServiceLoader.load(Driver.class);</span></span>
<span class="line"><span>            Iterator&lt;Driver&gt; driversIterator = loadedDrivers.iterator();</span></span>
<span class="line"><span>            try{</span></span>
<span class="line"><span>                while(driversIterator.hasNext()) {</span></span>
<span class="line"><span>                    driversIterator.next();</span></span>
<span class="line"><span>                }</span></span>
<span class="line"><span>            } catch(Throwable t) {</span></span>
<span class="line"><span>            // Do nothing</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            return null;</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>    });</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    println(&quot;DriverManager.initialize: jdbc.drivers = &quot; + drivers);</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    if (drivers == null || drivers.equals(&quot;&quot;)) {</span></span>
<span class="line"><span>        return;</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>    String[] driversList = drivers.split(&quot;:&quot;);</span></span>
<span class="line"><span>    println(&quot;number of Drivers:&quot; + driversList.length);</span></span>
<span class="line"><span>    for (String aDriver : driversList) {</span></span>
<span class="line"><span>        try {</span></span>
<span class="line"><span>            println(&quot;DriverManager.Initialize: loading &quot; + aDriver);</span></span>
<span class="line"><span>            Class.forName(aDriver, true,</span></span>
<span class="line"><span>                    ClassLoader.getSystemClassLoader());</span></span>
<span class="line"><span>        } catch (Exception ex) {</span></span>
<span class="line"><span>            println(&quot;DriverManager.Initialize: load failed: &quot; + ex);</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>}</span></span></code></pre></div><p>上面的代码主要步骤是：</p><ul><li>从系统变量中获取有关驱动的定义。</li><li>使用SPI来获取驱动的实现。</li><li>遍历使用SPI获取到的具体实现，实例化各个实现类。</li><li>根据第一步获取到的驱动列表来实例化具体实现类。</li></ul><p>我们主要关注2,3步，这两步是SPI的用法，首先看第二步，使用SPI来获取驱动的实现，对应的代码是：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>ServiceLoader&lt;Driver&gt; loadedDrivers = ServiceLoader.load(Driver.class);</span></span></code></pre></div><p>这里没有去<code>META-INF/services</code>目录下查找配置文件，也没有加载具体实现类，做的事情就是封装了我们的接口类型和类加载器，并初始化了一个迭代器。</p><p>接着看第三步，遍历使用SPI获取到的具体实现，实例化各个实现类，对应的代码如下：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>//获取迭代器</span></span>
<span class="line"><span>Iterator&lt;Driver&gt; driversIterator = loadedDrivers.iterator();</span></span>
<span class="line"><span>//遍历所有的驱动实现</span></span>
<span class="line"><span>while(driversIterator.hasNext()) {</span></span>
<span class="line"><span>    driversIterator.next();</span></span>
<span class="line"><span>}</span></span></code></pre></div><p>在遍历的时候，首先调用<code>driversIterator.hasNext()</code>方法，这里会搜索classpath下以及jar包中所有的<code>META-INF/services</code>目录下的<code>java.sql.Driver</code>文件，并找到文件中的实现类的名字，此时并没有实例化具体的实现类（ServiceLoader具体的源码实现在下面）。</p><p>然后是调用<code>driversIterator.next();</code>方法，此时就会根据驱动名字具体实例化各个实现类了。现在驱动就被找到并实例化了。</p><p>可以看下截图，我在测试项目中添加了两个jar包，<code>mysql-connector-java-6.0.6.jar</code>和<code>postgresql-42.0.0.0.jar</code>，跟踪到DriverManager中之后： ![[attachments/Pasted image 20240717102743.png]]</p><h3 id="spi机制-spring中spi机制" tabindex="-1">SPI机制 - Spring中SPI机制 <a class="header-anchor" href="#spi机制-spring中spi机制" aria-label="Permalink to &quot;SPI机制 - Spring中SPI机制&quot;">​</a></h3><p>在springboot的自动装配过程中，最终会加载<code>META-INF/spring.factories</code>文件，而加载的过程是由<code>SpringFactoriesLoader</code>加载的。从CLASSPATH下的每个Jar包中搜寻所有<code>META-INF/spring.factories</code>配置文件，然后将解析properties文件，找到指定名称的配置后返回。需要注意的是，其实这里不仅仅是会去ClassPath路径下查找，会扫描所有路径下的Jar包，只不过这个文件只会在Classpath下的jar包中。</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>public static final String FACTORIES_RESOURCE_LOCATION = &quot;META-INF/spring.factories&quot;;</span></span>
<span class="line"><span>// spring.factories文件的格式为：key=value1,value2,value3</span></span>
<span class="line"><span>// 从所有的jar包中找到META-INF/spring.factories文件</span></span>
<span class="line"><span>// 然后从文件中解析出key=factoryClass类名称的所有value值</span></span>
<span class="line"><span>public static List&lt;String&gt; loadFactoryNames(Class&lt;?&gt; factoryClass, ClassLoader classLoader) {</span></span>
<span class="line"><span>    String factoryClassName = factoryClass.getName();</span></span>
<span class="line"><span>    // 取得资源文件的URL</span></span>
<span class="line"><span>    Enumeration&lt;URL&gt; urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) : ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));</span></span>
<span class="line"><span>    List&lt;String&gt; result = new ArrayList&lt;String&gt;();</span></span>
<span class="line"><span>    // 遍历所有的URL</span></span>
<span class="line"><span>    while (urls.hasMoreElements()) {</span></span>
<span class="line"><span>        URL url = urls.nextElement();</span></span>
<span class="line"><span>        // 根据资源文件URL解析properties文件，得到对应的一组@Configuration类</span></span>
<span class="line"><span>        Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));</span></span>
<span class="line"><span>        String factoryClassNames = properties.getProperty(factoryClassName);</span></span>
<span class="line"><span>        // 组装数据，并返回</span></span>
<span class="line"><span>        result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>    return result;</span></span>
<span class="line"><span>}</span></span></code></pre></div><h2 id="spi机制深入理解" tabindex="-1"><a href="#spi机制深入理解">#</a> SPI机制深入理解 <a class="header-anchor" href="#spi机制深入理解" aria-label="Permalink to &quot;[#](#spi机制深入理解) SPI机制深入理解&quot;">​</a></h2><h3 id="spi机制通常怎么使用" tabindex="-1">SPI机制通常怎么使用 <a class="header-anchor" href="#spi机制通常怎么使用" aria-label="Permalink to &quot;SPI机制通常怎么使用&quot;">​</a></h3><p>看完上面的几个例子解析，应该都能知道大概的流程了：</p><ul><li>有关组织或者公司定义标准。</li><li>具体厂商或者框架开发者实现。</li><li>程序猿使用。</li></ul><h4 id="定义标准" tabindex="-1"><a href="#定义标准">#</a> 定义标准 <a class="header-anchor" href="#定义标准" aria-label="Permalink to &quot;[#](#定义标准) 定义标准&quot;">​</a></h4><p>定义标准，就是定义接口。比如接口<code>java.sql.Driver</code></p><h4 id="具体厂商或者框架开发者实现" tabindex="-1"><a href="#具体厂商或者框架开发者实现">#</a> 具体厂商或者框架开发者实现 <a class="header-anchor" href="#具体厂商或者框架开发者实现" aria-label="Permalink to &quot;[#](#具体厂商或者框架开发者实现) 具体厂商或者框架开发者实现&quot;">​</a></h4><p>厂商或者框架开发者开发具体的实现：</p><p>在<code>META-INF/services</code>目录下定义一个名字为接口全限定名的文件，比如<code>java.sql.Driver</code>文件，文件内容是具体的实现名字，比如<code>me.cxis.sql.MyDriver</code>。</p><p>写具体的实现<code>me.cxis.sql.MyDriver</code>，都是对接口Driver的实现。</p><h4 id="程序猿使用" tabindex="-1"><a href="#程序猿使用">#</a> 程序猿使用 <a class="header-anchor" href="#程序猿使用" aria-label="Permalink to &quot;[#](#程序猿使用) 程序猿使用&quot;">​</a></h4><p>我们会引用具体厂商的jar包来实现我们的功能：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>ServiceLoader&lt;Driver&gt; loadedDrivers = ServiceLoader.load(Driver.class);</span></span>
<span class="line"><span>//获取迭代器</span></span>
<span class="line"><span>Iterator&lt;Driver&gt; driversIterator = loadedDrivers.iterator();</span></span>
<span class="line"><span>//遍历</span></span>
<span class="line"><span>while(driversIterator.hasNext()) {</span></span>
<span class="line"><span>    driversIterator.next();</span></span>
<span class="line"><span>    //可以做具体的业务逻辑</span></span>
<span class="line"><span>}</span></span></code></pre></div><h4 id="使用规范" tabindex="-1">使用规范 <a class="header-anchor" href="#使用规范" aria-label="Permalink to &quot;使用规范&quot;">​</a></h4><p>最后总结一下jdk spi需要遵循的规范</p><p>![[attachments/Pasted image 20240717103458.png]]</p><h3 id="spi和api的区别是什么" tabindex="-1">SPI和API的区别是什么 <a class="header-anchor" href="#spi和api的区别是什么" aria-label="Permalink to &quot;SPI和API的区别是什么&quot;">​</a></h3><blockquote><p>SPI - “接口”位于“调用方”所在的“包”中</p></blockquote><ul><li>概念上更依赖调用方。</li><li>组织上位于调用方所在的包中。</li><li>实现位于独立的包中。</li><li>常见的例子是：插件模式的插件。</li></ul><blockquote><p>API - “接口”位于“实现方”所在的“包”中</p></blockquote><ul><li>概念上更接近实现方。</li><li>组织上位于实现方所在的包中。</li><li>实现和接口在一个包中。 ![[attachments/Pasted image 20240717103631.png]]</li></ul><p>![[attachments/Pasted image 20240717103637.png]]</p><h3 id="spi机制实现原理" tabindex="-1">### SPI机制实现原理 <a class="header-anchor" href="#spi机制实现原理" aria-label="Permalink to &quot;### SPI机制实现原理&quot;">​</a></h3><p>不妨看下JDK中<code>ServiceLoader&lt;S&gt;</code>方法的具体实现：</p><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>//ServiceLoader实现了Iterable接口，可以遍历所有的服务实现者</span></span>
<span class="line"><span>public final class ServiceLoader&lt;S&gt;</span></span>
<span class="line"><span>    implements Iterable&lt;S&gt;</span></span>
<span class="line"><span>{</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //查找配置文件的目录</span></span>
<span class="line"><span>    private static final String PREFIX = &quot;META-INF/services/&quot;;</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //表示要被加载的服务的类或接口</span></span>
<span class="line"><span>    private final Class&lt;S&gt; service;</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //这个ClassLoader用来定位，加载，实例化服务提供者</span></span>
<span class="line"><span>    private final ClassLoader loader;</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    // 访问控制上下文</span></span>
<span class="line"><span>    private final AccessControlContext acc;</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    // 缓存已经被实例化的服务提供者，按照实例化的顺序存储</span></span>
<span class="line"><span>    private LinkedHashMap&lt;String,S&gt; providers = new LinkedHashMap&lt;&gt;();</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    // 迭代器</span></span>
<span class="line"><span>    private LazyIterator lookupIterator;</span></span>
<span class="line"><span></span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //重新加载，就相当于重新创建ServiceLoader了，用于新的服务提供者安装到正在运行的Java虚拟机中的情况。</span></span>
<span class="line"><span>    public void reload() {</span></span>
<span class="line"><span>        //清空缓存中所有已实例化的服务提供者</span></span>
<span class="line"><span>        providers.clear();</span></span>
<span class="line"><span>        //新建一个迭代器，该迭代器会从头查找和实例化服务提供者</span></span>
<span class="line"><span>        lookupIterator = new LazyIterator(service, loader);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //私有构造器</span></span>
<span class="line"><span>    //使用指定的类加载器和服务创建服务加载器</span></span>
<span class="line"><span>    //如果没有指定类加载器，使用系统类加载器，就是应用类加载器。</span></span>
<span class="line"><span>    private ServiceLoader(Class&lt;S&gt; svc, ClassLoader cl) {</span></span>
<span class="line"><span>        service = Objects.requireNonNull(svc, &quot;Service interface cannot be null&quot;);</span></span>
<span class="line"><span>        loader = (cl == null) ? ClassLoader.getSystemClassLoader() : cl;</span></span>
<span class="line"><span>        acc = (System.getSecurityManager() != null) ? AccessController.getContext() : null;</span></span>
<span class="line"><span>        reload();</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //解析失败处理的方法</span></span>
<span class="line"><span>    private static void fail(Class&lt;?&gt; service, String msg, Throwable cause)</span></span>
<span class="line"><span>        throws ServiceConfigurationError</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span>        throw new ServiceConfigurationError(service.getName() + &quot;: &quot; + msg,</span></span>
<span class="line"><span>                                            cause);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    private static void fail(Class&lt;?&gt; service, String msg)</span></span>
<span class="line"><span>        throws ServiceConfigurationError</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span>        throw new ServiceConfigurationError(service.getName() + &quot;: &quot; + msg);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    private static void fail(Class&lt;?&gt; service, URL u, int line, String msg)</span></span>
<span class="line"><span>        throws ServiceConfigurationError</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span>        fail(service, u + &quot;:&quot; + line + &quot;: &quot; + msg);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //解析服务提供者配置文件中的一行</span></span>
<span class="line"><span>    //首先去掉注释校验，然后保存</span></span>
<span class="line"><span>    //返回下一行行号</span></span>
<span class="line"><span>    //重复的配置项和已经被实例化的配置项不会被保存</span></span>
<span class="line"><span>    private int parseLine(Class&lt;?&gt; service, URL u, BufferedReader r, int lc,</span></span>
<span class="line"><span>                          List&lt;String&gt; names)</span></span>
<span class="line"><span>        throws IOException, ServiceConfigurationError</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span>        //读取一行</span></span>
<span class="line"><span>        String ln = r.readLine();</span></span>
<span class="line"><span>        if (ln == null) {</span></span>
<span class="line"><span>            return -1;</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>        //#号代表注释行</span></span>
<span class="line"><span>        int ci = ln.indexOf(&#39;#&#39;);</span></span>
<span class="line"><span>        if (ci &gt;= 0) ln = ln.substring(0, ci);</span></span>
<span class="line"><span>        ln = ln.trim();</span></span>
<span class="line"><span>        int n = ln.length();</span></span>
<span class="line"><span>        if (n != 0) {</span></span>
<span class="line"><span>            if ((ln.indexOf(&#39; &#39;) &gt;= 0) || (ln.indexOf(&#39;\\t&#39;) &gt;= 0))</span></span>
<span class="line"><span>                fail(service, u, lc, &quot;Illegal configuration-file syntax&quot;);</span></span>
<span class="line"><span>            int cp = ln.codePointAt(0);</span></span>
<span class="line"><span>            if (!Character.isJavaIdentifierStart(cp))</span></span>
<span class="line"><span>                fail(service, u, lc, &quot;Illegal provider-class name: &quot; + ln);</span></span>
<span class="line"><span>            for (int i = Character.charCount(cp); i &lt; n; i += Character.charCount(cp)) {</span></span>
<span class="line"><span>                cp = ln.codePointAt(i);</span></span>
<span class="line"><span>                if (!Character.isJavaIdentifierPart(cp) &amp;&amp; (cp != &#39;.&#39;))</span></span>
<span class="line"><span>                    fail(service, u, lc, &quot;Illegal provider-class name: &quot; + ln);</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            if (!providers.containsKey(ln) &amp;&amp; !names.contains(ln))</span></span>
<span class="line"><span>                names.add(ln);</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>        return lc + 1;</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //解析配置文件，解析指定的url配置文件</span></span>
<span class="line"><span>    //使用parseLine方法进行解析，未被实例化的服务提供者会被保存到缓存中去</span></span>
<span class="line"><span>    private Iterator&lt;String&gt; parse(Class&lt;?&gt; service, URL u)</span></span>
<span class="line"><span>        throws ServiceConfigurationError</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span>        InputStream in = null;</span></span>
<span class="line"><span>        BufferedReader r = null;</span></span>
<span class="line"><span>        ArrayList&lt;String&gt; names = new ArrayList&lt;&gt;();</span></span>
<span class="line"><span>        try {</span></span>
<span class="line"><span>            in = u.openStream();</span></span>
<span class="line"><span>            r = new BufferedReader(new InputStreamReader(in, &quot;utf-8&quot;));</span></span>
<span class="line"><span>            int lc = 1;</span></span>
<span class="line"><span>            while ((lc = parseLine(service, u, r, lc, names)) &gt;= 0);</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>        return names.iterator();</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //服务提供者查找的迭代器</span></span>
<span class="line"><span>    private class LazyIterator</span></span>
<span class="line"><span>        implements Iterator&lt;S&gt;</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        Class&lt;S&gt; service;//服务提供者接口</span></span>
<span class="line"><span>        ClassLoader loader;//类加载器</span></span>
<span class="line"><span>        Enumeration&lt;URL&gt; configs = null;//保存实现类的url</span></span>
<span class="line"><span>        Iterator&lt;String&gt; pending = null;//保存实现类的全名</span></span>
<span class="line"><span>        String nextName = null;//迭代器中下一个实现类的全名</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        private LazyIterator(Class&lt;S&gt; service, ClassLoader loader) {</span></span>
<span class="line"><span>            this.service = service;</span></span>
<span class="line"><span>            this.loader = loader;</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        private boolean hasNextService() {</span></span>
<span class="line"><span>            if (nextName != null) {</span></span>
<span class="line"><span>                return true;</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            if (configs == null) {</span></span>
<span class="line"><span>                try {</span></span>
<span class="line"><span>                    String fullName = PREFIX + service.getName();</span></span>
<span class="line"><span>                    if (loader == null)</span></span>
<span class="line"><span>                        configs = ClassLoader.getSystemResources(fullName);</span></span>
<span class="line"><span>                    else</span></span>
<span class="line"><span>                        configs = loader.getResources(fullName);</span></span>
<span class="line"><span>                }</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            while ((pending == null) || !pending.hasNext()) {</span></span>
<span class="line"><span>                if (!configs.hasMoreElements()) {</span></span>
<span class="line"><span>                    return false;</span></span>
<span class="line"><span>                }</span></span>
<span class="line"><span>                pending = parse(service, configs.nextElement());</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            nextName = pending.next();</span></span>
<span class="line"><span>            return true;</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        private S nextService() {</span></span>
<span class="line"><span>            if (!hasNextService())</span></span>
<span class="line"><span>                throw new NoSuchElementException();</span></span>
<span class="line"><span>            String cn = nextName;</span></span>
<span class="line"><span>            nextName = null;</span></span>
<span class="line"><span>            Class&lt;?&gt; c = null;</span></span>
<span class="line"><span>            try {</span></span>
<span class="line"><span>                c = Class.forName(cn, false, loader);</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            if (!service.isAssignableFrom(c)) {</span></span>
<span class="line"><span>                fail(service, &quot;Provider &quot; + cn  + &quot; not a subtype&quot;);</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>            try {</span></span>
<span class="line"><span>                S p = service.cast(c.newInstance());</span></span>
<span class="line"><span>                providers.put(cn, p);</span></span>
<span class="line"><span>                return p;</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        public boolean hasNext() {</span></span>
<span class="line"><span>            if (acc == null) {</span></span>
<span class="line"><span>                return hasNextService();</span></span>
<span class="line"><span>            } else {</span></span>
<span class="line"><span>                PrivilegedAction&lt;Boolean&gt; action = new PrivilegedAction&lt;Boolean&gt;() {</span></span>
<span class="line"><span>                    public Boolean run() { return hasNextService(); }</span></span>
<span class="line"><span>                };</span></span>
<span class="line"><span>                return AccessController.doPrivileged(action, acc);</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        public S next() {</span></span>
<span class="line"><span>            if (acc == null) {</span></span>
<span class="line"><span>                return nextService();</span></span>
<span class="line"><span>            } else {</span></span>
<span class="line"><span>                PrivilegedAction&lt;S&gt; action = new PrivilegedAction&lt;S&gt;() {</span></span>
<span class="line"><span>                    public S run() { return nextService(); }</span></span>
<span class="line"><span>                };</span></span>
<span class="line"><span>                return AccessController.doPrivileged(action, acc);</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        public void remove() {</span></span>
<span class="line"><span>            throw new UnsupportedOperationException();</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //获取迭代器</span></span>
<span class="line"><span>    //返回遍历服务提供者的迭代器</span></span>
<span class="line"><span>    //以懒加载的方式加载可用的服务提供者</span></span>
<span class="line"><span>    //懒加载的实现是：解析配置文件和实例化服务提供者的工作由迭代器本身完成</span></span>
<span class="line"><span>    public Iterator&lt;S&gt; iterator() {</span></span>
<span class="line"><span>        return new Iterator&lt;S&gt;() {</span></span>
<span class="line"><span>            //按照实例化顺序返回已经缓存的服务提供者实例</span></span>
<span class="line"><span>            Iterator&lt;Map.Entry&lt;String,S&gt;&gt; knownProviders</span></span>
<span class="line"><span>                = providers.entrySet().iterator();</span></span>
<span class="line"><span></span></span>
<span class="line"><span>            public boolean hasNext() {</span></span>
<span class="line"><span>                if (knownProviders.hasNext())</span></span>
<span class="line"><span>                    return true;</span></span>
<span class="line"><span>                return lookupIterator.hasNext();</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>            public S next() {</span></span>
<span class="line"><span>                if (knownProviders.hasNext())</span></span>
<span class="line"><span>                    return knownProviders.next().getValue();</span></span>
<span class="line"><span>                return lookupIterator.next();</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>            public void remove() {</span></span>
<span class="line"><span>                throw new UnsupportedOperationException();</span></span>
<span class="line"><span>            }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>        };</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //为指定的服务使用指定的类加载器来创建一个ServiceLoader</span></span>
<span class="line"><span>    public static &lt;S&gt; ServiceLoader&lt;S&gt; load(Class&lt;S&gt; service,</span></span>
<span class="line"><span>                                            ClassLoader loader)</span></span>
<span class="line"><span>    {</span></span>
<span class="line"><span>        return new ServiceLoader&lt;&gt;(service, loader);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //使用线程上下文的类加载器来创建ServiceLoader</span></span>
<span class="line"><span>    public static &lt;S&gt; ServiceLoader&lt;S&gt; load(Class&lt;S&gt; service) {</span></span>
<span class="line"><span>        ClassLoader cl = Thread.currentThread().getContextClassLoader();</span></span>
<span class="line"><span>        return ServiceLoader.load(service, cl);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    //使用扩展类加载器为指定的服务创建ServiceLoader</span></span>
<span class="line"><span>    //只能找到并加载已经安装到当前Java虚拟机中的服务提供者，应用程序类路径中的服务提供者将被忽略</span></span>
<span class="line"><span>    public static &lt;S&gt; ServiceLoader&lt;S&gt; loadInstalled(Class&lt;S&gt; service) {</span></span>
<span class="line"><span>        ClassLoader cl = ClassLoader.getSystemClassLoader();</span></span>
<span class="line"><span>        ClassLoader prev = null;</span></span>
<span class="line"><span>        while (cl != null) {</span></span>
<span class="line"><span>            prev = cl;</span></span>
<span class="line"><span>            cl = cl.getParent();</span></span>
<span class="line"><span>        }</span></span>
<span class="line"><span>        return ServiceLoader.load(service, prev);</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>    public String toString() {</span></span>
<span class="line"><span>        return &quot;java.util.ServiceLoader[&quot; + service.getName() + &quot;]&quot;;</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span></span></span>
<span class="line"><span>}</span></span></code></pre></div><p><strong>首先</strong>，ServiceLoader实现了<code>Iterable</code>接口，所以它有迭代器的属性，这里主要都是实现了迭代器的<code>hasNext</code>和<code>next</code>方法。这里主要都是调用的<code>lookupIterator</code>的相应<code>hasNext</code>和<code>next</code>方法，<code>lookupIterator</code>是懒加载迭代器。</p><p><strong>其次</strong>，<code>LazyIterator</code>中的<code>hasNext</code>方法，静态变量PREFIX就是<code>”META-INF/services/”</code>目录，这也就是为什么需要在<code>classpath</code>下的<code>META-INF/services/</code>目录里创建一个以服务接口命名的文件。</p><p><strong>最后</strong>，通过反射方法<code>Class.forName()</code>加载类对象，并用<code>newInstance</code>方法将类实例化，并把实例化后的类缓存到<code>providers</code>对象中，(<code>LinkedHashMap&lt;String,S&gt;</code>类型）然后返回实例对象。</p><p>所以我们可以看到<code>ServiceLoader</code>不是实例化以后，就去读取配置文件中的具体实现，并进行实例化。而是等到使用迭代器去遍历的时候，才会加载对应的配置文件去解析，调用<code>hasNext</code>方法的时候会去加载配置文件进行解析，调用<code>next</code>方法的时候进行实例化并缓存。</p><p>所有的配置文件只会加载一次，服务提供者也只会被实例化一次，重新加载配置文件可使用<code>reload</code>方法。</p><h3 id="spi机制的缺陷" tabindex="-1"><a href="#spi机制的缺陷">#</a> SPI机制的缺陷 <a class="header-anchor" href="#spi机制的缺陷" aria-label="Permalink to &quot;[#](#spi机制的缺陷) SPI机制的缺陷&quot;">​</a></h3><p>通过上面的解析，可以发现，我们使用SPI机制的缺陷：</p><ul><li><p>不能按需加载，需要遍历所有的实现，并实例化，然后在循环中才能找到我们需要的实现。如果不想用某些实现类，或者某些类实例化很耗时，它也被载入并实例化了，这就造成了浪费。</p></li><li><p>获取某个实现类的方式不够灵活，只能通过 Iterator 形式获取，不能根据某个参数来获取对应的实现类。</p></li><li><p>多个并发多线程使用 ServiceLoader 类的实例是不安全的。</p></li></ul>`,83)]))}const g=a(l,[["render",i]]);export{v as __pageData,g as default};
