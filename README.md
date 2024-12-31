# Hello,welcome to visit my VuePressSite

这是一个 VuePress搭建的网站
[vitepress文档](https://vitepress.dev/zh/guide/getting-started)

# 初始化项目

## 前置准备

Node.js 18 及以上版本。

VitePress 可以单独使用，也可以安装到现有项目中。在这两种情况下，都可以使用以下方式安装它：

```npm 
npm add -D vitepress
```

## 安装向导

VitePress 附带一个命令行设置向导，可以帮助你构建一个基本项目。安装后，通过运行以下命令启动向导：

```npm
npx vitepress init
```

将需要回答几个简单的问题：

```
┌  Welcome to VitePress!
│
◇  Where should VitePress initialize the config?
│  ./docs
│
◇  Site title:
│  My Awesome Project
│
◇  Site description:
│  A VitePress Site
│
◆  Theme:
│  ● Default Theme (Out of the box, good-looking docs)
│  ○ Default Theme + Customization
│  ○ Custom Theme
└
```

## 启动并运行

该工具还应该将以下 npm 脚本注入到 package.json 中：

```
{
  ...
  "scripts": {
    "docs:dev": "vitepress dev docs",
    "docs:build": "vitepress build docs",
    "docs:preview": "vitepress preview docs"
  },
  ...
}
```

docs:dev 脚本将启动具有即时热更新的本地开发服务器。使用以下命令运行它：

```
npm run docs:dev
```

# Github部署

在项目的 `.github/workflows` 目录中创建一个名为 `deploy.yml` 的文件，其中包含这样的内容：

```
# 构建 VitePress 站点并将其部署到 GitHub Pages 的示例工作流程
#
name: Deploy VitePress site to Pages

on:
  # 在针对 `main` 分支的推送上运行。如果你
  # 使用 `master` 分支作为默认分支，请将其更改为 `master`
  push:
    branches: [main]

  # 允许你从 Actions 选项卡手动运行此工作流程
  workflow_dispatch:

# 设置 GITHUB_TOKEN 的权限，以允许部署到 GitHub Pages
permissions:
  contents: read
  pages: write
  id-token: write

# 只允许同时进行一次部署，跳过正在运行和最新队列之间的运行队列
# 但是，不要取消正在进行的运行，因为我们希望允许这些生产部署完成
concurrency:
  group: pages
  cancel-in-progress: false

jobs:
  # 构建工作
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v4
        with:
          fetch-depth: 0 # 如果未启用 lastUpdated，则不需要
      # - uses: pnpm/action-setup@v3 # 如果使用 pnpm，请取消注释
      # - uses: oven-sh/setup-bun@v1 # 如果使用 Bun，请取消注释
      - name: Setup Node
        uses: actions/setup-node@v4
        with:
          node-version: 20
          cache: npm # 或 pnpm / yarn
      - name: Setup Pages
        uses: actions/configure-pages@v4
      - name: Install dependencies
        run: npm ci # 或 pnpm install / yarn install / bun install
      - name: Build with VitePress
        run: npm run docs:build # 或 pnpm docs:build / yarn docs:build / bun run docs:build
      - name: Upload artifact
        uses: actions/upload-pages-artifact@v3
        with:
          path: docs/.vitepress/dist

  # 部署工作
  deploy:
    environment:
      name: github-pages
      url: ${{ steps.deployment.outputs.page_url }}
    needs: build
    runs-on: ubuntu-latest
    name: Deploy
    steps:
      - name: Deploy to GitHub Pages
        id: deployment
        uses: actions/deploy-pages@v4
```

> WARNING
>   
> 确保 VitePress 中的 `base` 选项配置正确。有关更多详细信息，请参阅[设置根路径](https://vitepress.dev/zh/guide/deploy#setting-a-public-base-path)。
> 设定 public 根目录
默认情况下，我们假设站点将部署在域名 (/) 的根路径上。如果站点在子路径中提供服务，例如 https://mywebsite.com/blog/ ，则需要在 VitePress 配置（config.mts）中将 base 选项设置为 '/blog/'。

例：如果你使用的是 Github（或 GitLab）页面并部署到 user.github.io/VitePressSite/，请将 base 设置为 /VitePressSite/。

2.在存储库设置中的“Pages”菜单项下，选择“Build and deployment > Source > **GitHub Actions**”。

3.将更改推送到 `main` 分支并等待 GitHub Action 工作流完成。你应该看到站点部署到 `https://<username>.github.io/[repository]/` 或 `https://<custom-domain>/`，这取决于你的设置。你的站点将在每次推送到 `main` 分支时自动部署。


## 开启Latex  
第一步 npm install markdown-it-mathjax3 -D 
第二步 在 .vitepress/config.ts 中添加
```text

export default defineConfig({
    markdown: {
        math: true
    },
    ....
```
