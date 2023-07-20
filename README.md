### 系统模块说明
- xfzcode-genie-admin 后台管理程序
    - xfzcode-genie-admin-online
      - 后台管理的中的低代码生成服务
    - xfzcode-genie-admin-system
      - 后台管理的主要的入口
- xfzcode-genie-common
  - 通用模块，被其他程序以jar包形式使用
- xfzcode-genie-base-core
  - 核心配置服务，以Jar的形式对外提供服务
- xfzcode-genie-gateway
  - 网关模块-控制api走向
- xfzcode-genie-unid
  - 生成分布式Id
- xfzcode-genie-server
  - xfzcode-genie-server-xxx
    - 提供对外接口：如大屏服务、对非管理平台服务

### 项目目录结构

```
项目结构
├─xfzcode-cloud-genie                               父POM： 项目依赖、modules组织
│  ├─xfzcode-genie-base-core                        系统核心模块： 工具类、config、权限、查询过滤器、注解等
│  ├─xfzcode-genie-common                           系统共用的模块
│  ├─xfzcode-genie-admin                            系统后台管理父
│  │  ├─xfzcode-genie-admin-system                  后台管理启动项目(8080）
│  │  ├─xfzcode-genie-admin-online                  后台管理低代码生成服务
│  ├─xfzcode-genie-server                           对外微服务父
     ├─xfzcode-genie-server-xxx                     微服务网关模块(9999)
```
