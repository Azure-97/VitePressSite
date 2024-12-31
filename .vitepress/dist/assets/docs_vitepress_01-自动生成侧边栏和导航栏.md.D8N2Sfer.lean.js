import{_ as s,c as n,a2 as e,o as p}from"./chunks/framework.D7kq4b6j.js";const h=JSON.parse('{"title":"11自动生成侧边栏和导航栏","description":"","frontmatter":{},"headers":[],"relativePath":"docs/vitepress/01-自动生成侧边栏和导航栏.md","filePath":"docs/vitepress/01-自动生成侧边栏和导航栏.md","lastUpdated":1721892855000}'),t={name:"docs/vitepress/01-自动生成侧边栏和导航栏.md"};function l(i,a,r,o,c,d){return p(),n("div",null,a[0]||(a[0]=[e(`<h1 id="_11自动生成侧边栏和导航栏" tabindex="-1">11自动生成侧边栏和导航栏 <a class="header-anchor" href="#_11自动生成侧边栏和导航栏" aria-label="Permalink to &quot;11自动生成侧边栏和导航栏&quot;">​</a></h1><p><a href="https://yarnpkg.com/package?name=vitepress-plugin-auto-nav-sidebar" target="_blank" rel="noreferrer">https://yarnpkg.com/package?name=vitepress-plugin-auto-nav-sidebar</a></p><nav class="table-of-contents"><ul><li><a href="#_22安装插件">22安装插件</a></li><li><a href="#添加插件">添加插件</a></li><li><a href="#配置">配置</a></li></ul></nav><h2 id="_22安装插件" tabindex="-1">22安装插件 <a class="header-anchor" href="#_22安装插件" aria-label="Permalink to &quot;22安装插件&quot;">​</a></h2><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>vitepress-plugin-auto-nav-sidebar</span></span></code></pre></div><h2 id="添加插件" tabindex="-1">添加插件 <a class="header-anchor" href="#添加插件" aria-label="Permalink to &quot;添加插件&quot;">​</a></h2><div class="language- vp-adaptive-theme"><button title="Copy Code" class="copy"></button><span class="lang"></span><pre class="shiki shiki-themes github-light github-dark vp-code" tabindex="0"><code><span class="line"><span>import VitePressPluginAutoNavSidebar from &#39;vitepress-plugin-auto-nav-sidebar&#39;</span></span>
<span class="line"><span> </span></span>
<span class="line"><span>export default defineConfig({</span></span>
<span class="line"><span>    vite: {</span></span>
<span class="line"><span>        plugins: [</span></span>
<span class="line"><span>            VitePressPluginAutoNavSidebar({</span></span>
<span class="line"><span>                documentRootPath: &#39;/docs&#39;,//文档文件所在的根路径</span></span>
<span class="line"><span>                ignoreIndexItems: true,//是否忽略首页的文件</span></span>
<span class="line"><span>                excludeFiles: [/.*demo.*/],//忽略的文件列表，支持正则匹配。</span></span>
<span class="line"><span>                excludeFolders: [&#39;demo&#39;],//忽略的文件夹列表，支持正则匹配。</span></span>
<span class="line"><span>                removeTitlePrefix: /^\\d+-/,//删除标题中的前缀，如果标题中包含该前缀，则删除。</span></span>
<span class="line"><span>                useTitleFromFileHeading: true,//如果设置为 true，则使用 .md 内容中的 h1 作为侧边栏和导航栏的标题。如果 h1 不存在，则使用文件名。</span></span>
<span class="line"><span>                useTitleFromFrontmatter: true,//如果设置为 true，则使用 .md 文件的 frontmatter 中的 title 字段作为侧边栏和导航栏的标题。如果不存在或者无法解析，则使用文件名。</span></span>
<span class="line"><span>                useSortFromTitle: true,//如果设置为 true，当菜单标题为数字开头时，按照数字排序。例如，如果文件为 [1-a.md , 10-a.md ,2-a.md]，最终会按照 [ 1-a.md , 2-a.md ,10-a.md] 排序。</span></span>
<span class="line"><span>                sortMenusBy: &#39;frontmatterOrder&#39;,//排序方式，支持按文件名、frontmatter 中的 order 字段、frontmatter 中的 date 字段排序。</span></span>
<span class="line"><span>                sortMenusOrder: &#39;asc&#39;,//排序顺序，支持升序和降序。</span></span>
<span class="line"><span>                collapsed: true,//是否折叠侧边栏。</span></span>
<span class="line"><span>                debugLog: true,//是否打印日志</span></span>
<span class="line"><span>            }),</span></span>
<span class="line"><span>        ],</span></span>
<span class="line"><span>    }</span></span>
<span class="line"><span>    .....</span></span></code></pre></div><div class="tip custom-block github-alert"><p class="custom-block-title">提示 插件配置成功的话，会在控制台输出 sidebar and nav generated successfully！。</p><p></p></div><h2 id="配置" tabindex="-1">配置 <a class="header-anchor" href="#配置" aria-label="Permalink to &quot;配置&quot;">​</a></h2><p><a href="https://yarnpkg.com/package?name=vitepress-plugin-auto-nav-sidebar" target="_blank" rel="noreferrer">配置文档</a></p>`,10)]))}const m=s(t,[["render",l]]);export{h as __pageData,m as default};
