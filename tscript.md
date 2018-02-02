## 基本类型

- 布尔值：`boolean`, `let isDone : boolean = true;`
- 数字：`number`, `let max : number = 1234;`
- 字符串： 
    + 普通单双引号,`string`, `let str : string = "23456";`
    + 模板字符串，使用反引号` `` `, 可以定义多行文本和内嵌表达式`let abd : string = ``my name is ${str} ;`
- 数组：
    + 普通数组：`let list : number[] = [1, 2, 3];`
    + 数组泛型：`let list : Array<number> = [1, 2, 3];`
- 元组：表示一个已知元素数量和类型的数组，各元素类型不必相同
    + `let x : [string, number] = ["hello", 345];`
- 枚举：可以为一组数值赋予友好的名字，`enum Color {Red, Green}; let c : Color = Color.Red;`，默认下标为0
- 任意值： `any`,可为任意值，具体类型会自动进行推断，与Object区别于可调用不同类型的方法
- 空值：`void`，没有任何返回值，空值变量取值：`null` `undefined`
- `null undefined`的各自类型为`null undefined`，默认为所有类型的子类型

> 类型断言:类似于类型转换，明确值类型； 有两种写法：`let someone: any = "i'm string";`
> - 尖括号：` let len: number = (<string> someone).length;`
> - as语法：` let len: number = (someone as string).length;`

## 变量声明

- `var`声明：可以在包含它的函数，模块，命名空间或全局作用域内部任何位置被访问
- `let`声明：词法作用域或块作用域
- `const`声明：作用域同`let`，但变量不能被重复赋值
- 数组解构：`let input = [1, 2]; let [f1, f2] = input; console.log(f1); [f1, f2] = [f2, f1];`
- 对象解构：`let o = {a:"1", b:"2", c: "3"}; let {a, b} = o;` 
- 属性重命名： `let {a: newName, b: newName2} = o;`
   
## 接口
> 为代码定义契约

- 可选属性： `interface a { a2: string; a3?:number}`, 对可能存在的属性进行预定义，可以捕获引用不存在变量的错误
- 只读属性：`interface a {readonly a2: string}; let ra: ReadonlyArray<string> = ["1", "2"]`
- 函数类型：`interface SearchFun{ (s2: string, s3: string): boolean;}`


