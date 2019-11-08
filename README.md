# AsyncPermission

使用链式的方式请求权限，屏蔽掉权限获取的过程和 `onRequestPermissionsResult` 判断。

## 判断权限

```java
AsyncPermission.with(Fragment/FragmentActivity)
    .check(String...permissions); //如果不想默认测试一次，可以使用 checkNoTest()
```

## 申请权限

```java
AsyncPermission.with(Fragment/FragmentActivity)
    .request(String permission...) //如果不想默认测试一次，可以使用 requestNoTest()
    .onAllGranted(OnAsyncPermissionGrantedListener listener)
    .onGranted(OnAsyncPermissionGrantedListener listener)
    .onDenied(OnAsyncPermissionDeniedListener listener);
```

## 简述原理

权限操作主要有两个行为：判断和请求。因为低于Android版本23和高于23的逻辑处理是不一样的，所以将这两个行为定义为了两个抽象的接口。通过链式构造方法，填写需要的参数，内部进行判断使用不同的实现。下面是权限请求的时序图，检查权限的流程类似。
![img](https://file.2fun.xyz/minglin_async_permission_uml_20190812.png)

## 权限判断的小经验

通常情况下面我们使用 `context.checkSelfPermission(permission)` 进行权限判断，但是由于安卓系统开源导致的碎片问题，会有检查结果错误的情况发生，所以谷歌有提供了另外一个类 `AppOpsManager` 解决碎片化导致的权限检查错误问题。但是仅仅通过这两个进行权限判断还是不够，比如在 `锤子手机` 里面，如果用户在第一次请求权限的时候拒绝了，那么以后每次检查权限返回的都是已经拥有权限，即使你去请求权限他也不会鸟你。正当我不知道怎么办的时候，我发现**AndPermission**使用了一个很骚的操作，那就执行运行需要这个权限的代码，测试一下，报错即说明没有获取到权限，否则就是已经获取到了。所以我最后的权限检查做了三层：第一步使用标准的方式检查，第二步使用 `AppOpsManager` ，第三步使用 `*Test` 测试权限。

## 参考项目

[**AndPermission**]( https://github.com/yanzhenjie/AndPermission )