# The Navigation Architecture Component

![](img/img1.png)

## Вступление

Построение навигации в android приложении всегда была проблемой многих разработчиков. Сообщество написало много библиотек которые в какой-то мере решали проблемы, но многие ждали решение именно от Google. На Google io 2018 анонсируют Navigation Architecture Component которое входит в состав Jetpack. На данный момент данная технология находится в разработке, но уже доступна в Android Studio 3.2 Beta.

## Установка и первоначальная настройка проекта

Для добавим необходимые зависимости в 'build/gradle'

### Основные библиотеки
~~~ groovy
dependencies {
    implementation "android.arch.navigation:navigation-fragment:1.0.0-alpha02"
    implementation "android.arch.navigation:navigation-ui:1.0.0-alpha02"
    androidTestImplementation "android.arch.navigation:navigation-testing:1.0.0-alpha02"
}
~~~
### Ресурсы
В папке res нужно создать ресурсы для навигации New -> Android resource file
и выбрать Resource type Navigation, укажите имя файла, кликнете OK.

![](img/img_create_nav.png)

Создается новая директория navigation в которой будет находится ранее созданный файл.

## Обзор начального функционала 

Файл навигационного графа имеет два режима разработки editor и text

### Navigation Editor

Предназначен для разработки навигации при помощи редактора без написание кода. Имеет три столбца

![](img/img1.png)

### Text

В этом режиме мы пишем xml код 

~~~ html
...
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/test"
    app:startDestination="@id/mainActivity">

    <activity
        android:id="@+id/mainActivity"
        android:name="andrey.murzin.navigation.MainActivity"
        android:label="activity_main"
        tools:layout="@layout/activity_main" />
</navigation>
...
~~~

## Создание простейшего графа навигации

Библиотека navigation предназначена для реализации “single activity application” поэтому  activity выступает в роли "хоста" графа.Если в приложении несколько activity то каждая активити будет иметь свой граф.

Перейдем к практики и напишем простой навигационный граф постепенно усложняя его

### Добавление экранов
* В редакторе графов кликните New Destination . Откроется диалоговое окно New Destination.
* Кликните Create blank destination или выберете существующий фрагмент или активность. Откроется диалоговое окно Android Component.
* Введите имя в поле Fragment Name. 
* Введите имя в поле Fragment Layout Name. Это имя будет присвоено layout файлу фрагмента.
* Нажмите Finish. 



















