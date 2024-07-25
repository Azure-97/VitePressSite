---
order: 1
---

## Markdown 扩展  

VitePress 带有内置的 Markdown 扩展。

## 标题锚点  

标题会自动应用锚点。可以使用 `markdown.anchor` 选项配置锚点的渲染。

### 自定义锚点 

要为标题指定自定义锚点而不是使用自动生成的锚点，请向标题添加后缀：

这允许将标题链接为 `#my-anchor`，而不是默认的 `#使用自定义锚点`。

## 链接 

内部和外部链接都会被特殊处理。

### 内部链接  

内部链接将转换为单页导航的路由链接。此外，子目录中包含的每个 `index.md` 都会自动转换为 `index.html`，并带有相应的 URL `/`。

例如，给定以下目录结构：

```auto
.
├─ index.md
├─ foo
│  ├─ index.md
│  ├─ one.md
│  └─ two.md
└─ bar
   ├─ index.md
   ├─ three.md
   └─ four.md
```

假设现在处于 `foo/one.md` 文件中：

md

```auto
[Home](/) <!-- 将用户导航至根目录下的 index.html -->
[foo](/foo/) <!-- 将用户导航至目录 foo 下的 index.html -->
[foo heading](./#heading) <!-- 将用户锚定到目录 foo 下的index文件中的一个标题上 -->
[bar - three](../bar/three) <!-- 可以省略扩展名 -->
[bar - three](../bar/three.md) <!-- 可以添加 .md -->
[bar - four](../bar/four.html) <!-- 或者可以添加 .html -->
```

### 页面后缀 

默认情况下，生成的页面和内部链接带有 `.html` 后缀。

### 外部链接 

外部链接带有 `target="_blank" rel="noreferrer"`：

+   [vuejs.org](https://cn.vuejs.org/)
+   [VitePress on GitHub](https://github.com/vuejs/vitepress)

## frontmatter  

[YAML 格式的 frontmatter](https://jekyllrb.com/docs/front-matter/) 开箱即用：

yaml

```auto
---
title: Blogging Like a Hacker
lang: en-US
---
```

此数据将可用于页面的其余部分，以及所有自定义和主题组件。

更多信息，参见 [frontmatter](https://vitepress.dev/zh/reference/frontmatter-config)。

## GitHub 风格的表格

**输入**

```auto
| Tables        |      Are      |  Cool |
| ------------- | :-----------: | ----: |
| col 3 is      | right-aligned | $1600 |
| col 2 is      |   centered    |   $12 |
| zebra stripes |   are neat    |    $1 |
```

**输出**

| Tables | Are | Cool |
| --- | --- | --- |
| col 3 is | right-aligned | $1600 |
| col 2 is | centered | $12 |
| zebra stripes | are neat | $1 |

## Emoji 🎉 

**输入**
```text
:tada: :100:

```
**输出**

🎉 💯

这里可以找到[所有支持的 emoji 列表](https://github.com/markdown-it/markdown-it-emoji/blob/master/lib/data/full.mjs)。

## 目录表 (TOC)  

**输入**
```text
[[toc]]
```
**输出**
[[toc]]


可以使用 `markdown.toc` 选项配置 TOC 的呈现效果。

## 自定义容器 [​](#custom-containers)

自定义容器可以通过它们的类型、标题和内容来定义。

### 默认标题 [​](#default-title)

**输入**
```auto
::: info
This is an info box.
:::

::: tip
This is a tip.
:::

::: warning
This is a warning.
:::

::: danger
This is a dangerous warning.
:::

::: details
This is a details block.
:::
```

**输出**
::: info
This is an info box.
:::

::: tip
This is a tip.
:::

::: warning
This is a warning.
:::

::: danger
This is a dangerous warning.
:::

::: details
This is a details block.
:::

### 自定义标题 
可以通过在容器的 "type" 之后附加文本来设置自定义标题。


````
::: danger STOP
危险区域，请勿继续
:::

::: details 点我查看代码
```js
console.log('Hello, VitePress!')
```
:::
````



::: danger STOP
危险区域，请勿继续
:::

::: details 点我查看代码
```js
console.log('Hello, VitePress!')
```
:::




### `raw`  
这是一个特殊的容器，可以用来防止与 VitePress 的样式和路由冲突。这在记录组件库时特别有用。可能还想查看 whyframe 以获得更好的隔离。
https://vitepress.dev/zh/guide/markdown#raw

## GitHub 风格的警报 

VitePress 同样支持以标注的方式渲染 [GitHub 风格的警报](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#alerts)。它们和[自定义容器](#custom-containers)的渲染方式相同。

md

```auto
> [!NOTE]
> 强调用户在快速浏览文档时也不应忽略的重要信息。

> [!TIP]
> 有助于用户更顺利达成目标的建议性信息。

> [!IMPORTANT]
> 对用户达成目标至关重要的信息。

> [!WARNING]
> 因为可能存在风险，所以需要用户立即关注的关键内容。

> [!CAUTION]
> 行为可能带来的负面影响。
```
> [!NOTE]
> 强调用户在快速浏览文档时也不应忽略的重要信息。

> [!TIP]
> 有助于用户更顺利达成目标的建议性信息。

> [!IMPORTANT]
> 对用户达成目标至关重要的信息。

> [!WARNING]
> 因为可能存在风险，所以需要用户立即关注的关键内容。

> [!CAUTION]
> 行为可能带来的负面影响。

## 代码块中的语法高亮 
```js
export default {
  name: 'MyComponent',
  // ...
}
```



## 在代码块中实现行高亮  
方式一 `js{1,4,6-8}`
```js{1,4,6-8}
export default { // Highlighted
  data () {
    return {
      msg: `Highlighted!
      This line isn't highlighted,
      but this and the next 2 are.`,
      motd: 'VitePress is awesome',
      lorem: 'ipsum'
    }
  }
}
```
方式二： ` // [!code highlight]`
```js
export default {
  data () {
    return {
      msg: 'Highlighted!' // [!code highlight]
    }
  }
}
```


## 代码块中聚焦  

在某一行上添加 `// [!code focus]` 注释将聚焦它并模糊代码的其他部分。

此外，可以使用 `// [!code focus:<lines>]` 定义要聚焦的行数。

 

```js
export default {
  data () {
    return {
      msg: 'Focused!' // [!code focus]
    }
  }
}
```
 

## 代码块中的颜色差异

在某一行添加 `// [!code --]` 或 `// [!code ++]` 注释将会为该行创建 diff，同时保留代码块的颜色。
 

```auto
export default {
  data () {
    return {
      msg: 'Removed' // [!code --]
      msg: 'Added' // [!code ++]
    }
  }
}
```
 

## 高亮“错误”和“警告” 

在某一行添加 `// [!code warning]` 或 `// [!code error]` 注释将会为该行相应的着色。
 

```auto
export default {
  data () {
    return {
      msg: 'Error', // [!code error]
      msg: 'Warning' // [!code warning]
    }
  }
}
```
 

## 行号  

可以通过以下配置为每个代码块启用行号：

```auto
export default {
  markdown: {
    lineNumbers: true
  }
}
```

查看 [`markdown` 选项](https://vitepress.dev/zh/reference/site-config#markdown) 获取更多信息。

可以在代码块中添加 `:line-numbers` / `:no-line-numbers` 标记来覆盖在配置中的设置。

还可以通过在 `:line-numbers` 之后添加 `=` 来自定义起始行号，例如 `:line-numbers=2` 表示代码块中的行号从 2 开始。

**输入**
 
```ts:line-numbers {1}
// 启用行号
const line2 = 'This is line 2'
const line3 = 'This is line 3'
```

```ts:line-numbers=2 {1}
// 行号已启用，并从 2 开始
const line3 = 'This is line 3'
const line4 = 'This is line 4'
```
 
 
## 导入代码片段 
https://vitepress.dev/zh/guide/markdown#import-code-snippets

## 代码组  

可以像这样对多个代码块进行分组：
````
::: code-group

```js [config.js]
/**
 * @type {import('vitepress').UserConfig}
 */
const config = {
  // ...
}

export default config
```

```ts [config.ts]
import type { UserConfig } from 'vitepress'

const config: UserConfig = {
  // ...
}

export default config
```

:::
````
::: code-group

```js [config.js]
/**
 * @type {import('vitepress').UserConfig}
 */
const config = {
  // ...
}

export default config
```

```ts [config.ts]
import type { UserConfig } from 'vitepress'

const config: UserConfig = {
  // ...
}

export default config
```

:::


也可以在代码组中导入代码片段：
https://vitepress.dev/zh/guide/markdown#code-groups


## 包含 markdown 文件  

https://vitepress.dev/zh/guide/markdown#markdown-file-inclusion

## 数学方程 
https://vitepress.dev/zh/guide/markdown#math-equations

## 图片懒加载  

通过在配置文件中将 `lazyLoading` 设置为 `true`，可以为通过 markdown 添加的每张图片启用懒加载。

js

```auto
export default {
  markdown: {
    image: {
      // 默认禁用图片懒加载
      lazyLoading: true
    }
  }
}
```

## 高级配置  

VitePress 使用 [markdown-it](https://github.com/markdown-it/markdown-it) 作为 Markdown 渲染器。上面提到的很多扩展功能都是通过自定义插件实现的。可以使用 `.vitepress/config.js` 中的 `markdown` 选项来进一步自定义 `markdown-it` 实例。

js

```auto
import { defineConfig } from 'vitepress'
import markdownItAnchor from 'markdown-it-anchor'
import markdownItFoo from 'markdown-it-foo'

export default defineConfig({
  markdown: {
    // markdown-it-anchor 的选项
    // https://github.com/valeriangalliat/markdown-it-anchor#usage
    anchor: {
      permalink: markdownItAnchor.permalink.headerLink()
    },
    // @mdit-vue/plugin-toc 的选项
    // https://github.com/mdit-vue/mdit-vue/tree/main/packages/plugin-toc#options
    toc: { level: [1, 2] },
    config: (md) => {
      // 使用更多的 Markdown-it 插件！
      md.use(markdownItFoo)
    }
  }
})
```

请查看[配置参考：站点配置](https://vitepress.dev/zh/reference/site-config#markdown)来获取完整的可配置属性列表。
