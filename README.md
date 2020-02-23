# actuator
应用监控：出错时自动报警等，健康状况、内存情况第一个问题：端点的配置

spring boot 端点官方文档
https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/production-ready-endpoints.html

开启redis
端点：
就是一个请求的url地址
端点开启 不等于端点能用，还有一个概念是端点暴漏
#端点暴漏 ,暴漏端点：端点用 ，号分隔例health,info
# * 表示暴漏所有
management.endpoints.web.exposure.include=*

直接暴漏不安全：需要加安全
加security
-------------------------------------
健康指示器
{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 263882534912,
                "free": 254692610048,
                "threshold": 10485760
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
添加redis后，就显示redis的健康情况
{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 263882534912,
                "free": 254692532224,
                "threshold": 10485760
            }
        },
        "ping": {
            "status": "UP"
        },
        "redis": {
            "status": "UP",
            "details": {
                "version": "3.0.504"
            }
        }
    }
}

info 包含三大块
1、自定义信息
2、git信息
3、项目构建信息

1、自定义信息
#info 端点配置
info.app.encoding=@project.build.sourceEncoding@
info.app.java.source=@java.version@
info.java.target=@java.version@
info.author.name=codefarmer
info.author.address=www.codefarmer.fun
例；显示
{
    "app": {
        "encoding": "UTF-8",
        "java": {
            "source": "1.8.0_181"
        }
    },
    "java": {
        "target": "1.8.0_181"
    },
    "author": {
        "name": "codefarmer",
        "address": "www.codefarmer.fun"
    }
}
代码优先级高于properties配置
{
    "app": {
        "encoding": "UTF-8",
        "java": {
            "source": "1.8.0_181"
        }
    },
    "java": {
        "target": "1.8.0_181"
    },
    "author": {
        "email": "wangsong0210@gmail.com"
    }
}
2、增加git信息
pom中
	<build>
        <plugins>
		<!--项目中有提交信息可不用-->
            <!--自动从当前项目中提起git信息生成git的properties-->
            <!--加插件-->
            <!--双击Plugins中git-commit-id:revision生成gitproperties-->
            <plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

生成后：target->classes
{
    "app": {
        "encoding": "UTF-8",
        "java": {
            "source": "1.8.0_181"
        }
    },
    "java": {
        "target": "1.8.0_181"
    },
    "author": {
        "email": "wangsong0210@gmail.com"
    },
    "git": {
        "build": {
            "host": "DESKTOP-9EQ4382",
            "version": "0.0.1-SNAPSHOT",
            "time": "2020-02-23T12:10:10Z",
            "user": {
                "name": "zhanglianhui",
                "email": "405609791@qq.com"
            }
        },
        "branch": "master",
        "commit": {
            "message": {
                "short": "first add",
                "full": "first add"
            },
            "id": {
                "describe": "607648b-dirty",
                "abbrev": "607648b",
                "describe-short": "607648b-dirty",
                "full": "607648bd8e4814ba068c7f1d975a834450d17c37"
            },
            "time": "2020-02-23T11:58:03Z",
            "user": {
                "email": "405609791@qq.com",
                "name": "zhanglianhui"
            }
        },
        "closest": {
            "tag": {
                "name": "",
                "commit": {
                    "count": ""
                }
            }
        },
        "local": {
            "branch": {
                "ahead": "NO_REMOTE",
                "behind": "NO_REMOTE"
            }
        },
        "dirty": "true",
        "remote": {
            "origin": {
                "url": "Unknown"
            }
        },
        "tags": "",
        "total": {
            "commit": {
                "count": "1"
            }
        }
    }
}
3、构建信息
	<build>
        <plugins>
            <!--自动从当前项目中提起git信息生成git的properties-->
            <!--加插件-->
            <!--双击Plugins中git-commit-id:revision生成gitproperties-->
            <plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
				<!--添加，自动生成guild-info.properties,在Plugins中spring-boot-->
                <executions>
                    <execution>
                        <goals>
                            <goal>build-info</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
	生成后：target->classes->META-INF
显示：
{
    "app": {
        "encoding": "UTF-8",
        "java": {
            "source": "1.8.0_181"
        }
    },
    "java": {
        "target": "1.8.0_181"
    },
    "author": {
        "email": "wangsong0210@gmail.com"
    },
    "git": {
        "build": {
            "host": "DESKTOP-9EQ4382",
            "version": "0.0.1-SNAPSHOT",
            "time": "2020-02-23T12:10:10Z",
            "user": {
                "name": "zhanglianhui",
                "email": "405609791@qq.com"
            }
        },
        "branch": "master",
        "commit": {
            "message": {
                "short": "first add",
                "full": "first add"
            },
            "id": {
                "describe": "607648b-dirty",
                "abbrev": "607648b",
                "describe-short": "607648b-dirty",
                "full": "607648bd8e4814ba068c7f1d975a834450d17c37"
            },
            "time": "2020-02-23T11:58:03Z",
            "user": {
                "email": "405609791@qq.com",
                "name": "zhanglianhui"
            }
        },
        "closest": {
            "tag": {
                "name": "",
                "commit": {
                    "count": ""
                }
            }
        },
        "local": {
            "branch": {
                "ahead": "NO_REMOTE",
                "behind": "NO_REMOTE"
            }
        },
        "dirty": "true",
        "remote": {
            "origin": {
                "url": "Unknown"
            }
        },
        "tags": "",
        "total": {
            "commit": {
                "count": "1"
            }
        }
    },
	//build信息
    "build": {
        "version": "0.0.1-SNAPSHOT",
        "artifact": "actuator",
        "name": "actuator",
        "group": "fun.codefarmer",
        "time": "2020-02-23T12:36:54.877Z"
    }
}	