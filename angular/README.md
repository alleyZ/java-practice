## 模块

- 每个应用至少有一个模块（根模块）
- 每个模块都是一个带有`@NgModule`装饰器的类
- `@NgModule`的重要属性：
    + `declarations` 声明本模块拥有的视图类，主要由`组件(components)` `指令(directives)` `管道(pipes)`
    + `exports`可用于其他模块的组件模板
    + `imports` 本模块声明的组件模板需要的类所在的其他模块
    + `providers` 服务的创建者，并加入到全局服务列表中，可用于应用任何部分
    + `bootstrap` 指定应用的主视图（根组件），只有根模块才能设置此项
    