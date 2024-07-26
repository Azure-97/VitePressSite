import {defineConfig} from 'vitepress'

import VitePressPluginAutoNavSidebar from 'vitepress-plugin-auto-nav-sidebar'


// https://vitepress.dev/reference/site-config
export default defineConfig({

    vite: {
        plugins: [
            VitePressPluginAutoNavSidebar({ //自动生成导航栏和侧边栏插件配置
                documentRootPath: '/docs',//文档文件所在的根路径
                ignoreIndexItems: true,//是否忽略首页的文件
                excludeFiles: [/.*demo.*/],//忽略的文件列表，支持正则匹配。
                excludeFolders: ['.obsidian','.trash','attachments','code'],//忽略的文件夹列表，支持正则匹配。
                removeTitlePrefix: /^\d+-/,//删除标题中的前缀，如果标题中包含该前缀，则删除。
                useTitleFromFileHeading: false,//如果设置为 true，则使用 .md 内容中的 h1 作为侧边栏和导航栏的标题。如果 h1 不存在，则使用文件名。
                useTitleFromFrontmatter: false,//如果设置为 true，则使用 .md 文件的 frontmatter 中的 title 字段作为侧边栏和导航栏的标题。如果不存在或者无法解析，则使用文件名。
                useSortFromTitle: true,//如果设置为 true，当菜单标题为数字开头时，按照数字排序。例如，如果文件为 [1-a.md , 10-a.md ,2-a.md]，最终会按照 [ 1-a.md , 2-a.md ,10-a.md] 排序。
                sortMenusBy: 'fileName',//排序方式，支持按文件名、frontmatter 中的 order 字段、frontmatter 中的 date 字段排序。
                sortMenusOrder: 'asc',//排序顺序，支持升序和降序。
                collapsed: true,//是否折叠侧边栏。
                debugLog: true,//是否打印日志
            }),
        ],
    },

    lastUpdated: true,//显示md最后更新时间
    base: '/VitePressSite/',
    title: "AzureWang",
    description: "AzureWang‘s VitePress Site",
    themeConfig: {
       // https://vitepress.dev/reference/default-theme-config

        //开启本地搜索
        search: {
            provider: 'local'
        },


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
            {icon: 'github', link: 'https://github.com/Azure-97'}
        ],
        footer: {
            message: '你好呀，这是我的小网站',
            copyright: 'Copyright © 2024-AzureWang'
        },

    },
    // markdown: {
    //     lineNumbers: true//代码块启用行号
    // },
})


