package cn.lz.base.groovy

def helloGroovy(String name = "world") {
    println "hello, ${name}!"

}

helloGroovy "小李"

static def pipeline(closure) {
    closure()
}

pipeline {
    print "hello, 闭包"
}