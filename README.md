# The Navigation Architecture Component

![](img/img1.png)

##Вступление

Построение навигации в android приложении всегда была проблемой многих разработчиков. Сообщество написало много библиотек которые в какой-то мере решали проблемы, но многие ждали решение именно от Google. На Google io 2018 анонсируют Navigation Architecture Component которое входит в состав Jetpack. На данный момент данная технология находится в разработке, но уже доступна в Android Studio 3.2 Beta.

##Установка и первоначальная настройка проекта

Для добавим необходимые зависимости в 'build/gradle'

###Основные библиотеки
~~~ groovy
dependencies {
    implementation "android.arch.navigation:navigation-fragment:1.0.0-alpha02"
    implementation "android.arch.navigation:navigation-ui:1.0.0-alpha02"
    androidTestImplementation "android.arch.navigation:navigation-testing:1.0.0-alpha02"
}
~~~

