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

## 类

- public：默认为public，
- private：可被子类共享
- readonly: 只读属性必须在构造函数中或者声明时赋值
- 派生类的构造函数必须调用`super()`
- 在构造函数里边访问`this`时，必须调用`super()`
- 存取器：只有get不带set的存取器自动被推断为readonly
- 静态属性： static
- 抽象类，抽象方法：abstract
- 接口可以`extends`类

## 函数

- 函数类型，可以为函数定义入参类型以及返回值类型，也可根据返回值推断类型
- 可选参数： `function a(a?: number) {}`
- 剩余参数，`function a(a: number, ...b: string[]){}`
- 箭头函数：箭头函数能保存函数创建时的this值
- this： `function (this: Deck) { this.name;}`
- 重载：在定义重载时，需要将最精确的放在最上边

## 泛型

- `function A<T>(a: T): T {}`
- 和Java类似

## 枚举

- 普通枚举：如果没有初始化方法被当做常数枚举
- 常数枚举：在使用的地方被嵌入进来
- 外部枚举：没有初始化方法时被当做需要计算的枚举；