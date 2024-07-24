import {defineConfig} from 'vitepress'

import VitePressPluginAutoNavSidebar from 'vitepress-plugin-auto-nav-sidebar'


// https://vitepress.dev/reference/site-config
export default defineConfig({
    vite: {
        plugins: [
            VitePressPluginAutoNavSidebar({
                documentRootPath: '/docs',//文档文件所在的根路径
                ignoreIndexItems: true,//是否忽略首页的文件
                excludeFiles: [/.*demo.*/],//忽略的文件列表，支持正则匹配。
                excludeFolders: ['demo'],//忽略的文件夹列表，支持正则匹配。
                removeTitlePrefix: /^\d+-/,//删除标题中的前缀，如果标题中包含该前缀，则删除。
                useTitleFromFileHeading: true,//如果设置为 true，则使用 .md 内容中的 h1 作为侧边栏和导航栏的标题。如果 h1 不存在，则使用文件名。
                useTitleFromFrontmatter: true,//如果设置为 true，则使用 .md 文件的 frontmatter 中的 title 字段作为侧边栏和导航栏的标题。如果不存在或者无法解析，则使用文件名。
                useSortFromTitle: true,//如果设置为 true，当菜单标题为数字开头时，按照数字排序。例如，如果文件为 [1-a.md , 10-a.md ,2-a.md]，最终会按照 [ 1-a.md , 2-a.md ,10-a.md] 排序。
                sortMenusBy: 'frontmatterOrder',//排序方式，支持按文件名、frontmatter 中的 order 字段、frontmatter 中的 date 字段排序。
                sortMenusOrder: 'asc',//排序顺序，支持升序和降序。
                collapsed: true,//是否折叠侧边栏。
                debugLog: true,//是否打印日志
            }),
        ],
    },


    base: '/VitePressSite/',
    title: "My Awesome Project",
    description: "A VitePress Site",
    themeConfig: {
       // https://vitepress.dev/reference/default-theme-config




        // nav: [
        //     {text: 'Home', link: '/'},
        //     {text: 'Examples', link: '/markdown-examples'},
        //     {text: 'root', link: '/root'}
        // ],

        // sidebar: [
        //     {
        //         text: 'Examples',
        //         items: [
        //             {text: 'Markdown Examples', link: '/markdown-examples'},
        //             {text: 'Runtime API Examples', link: '/api-examples'}
        //         ]
        //     }
        // ],


        socialLinks: [
            {icon: 'github', link: 'https://github.com/vuejs/vitepress'}
        ]
    }
})


