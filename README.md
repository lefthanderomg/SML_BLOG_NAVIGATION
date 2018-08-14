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
~~~

## Создание графа навигации

Библиотека navigation предназначена для реализации “single activity application” поэтому  activity выступает в роли "хоста" графа.Если в приложении несколько activity то каждая activity будет иметь свой граф.

Перейдем к практики и напишем простой навигационный граф постепенно усложняя его.

### Добавление экранов
1) В редакторе графов кликните New Destination . Откроется диалоговое окно New Destination.
2) Кликните Create blank destination или выберете существующий фрагмент или активность. Откроется диалоговое окно Android Component.
3) Введите имя в поле Fragment Name. 
4) Введите имя в поле Fragment Layout Name. Это имя будет присвоено layout файлу фрагмента.
5) Нажмите Finish. 

![](img/img_create.png)

### Соединение экранов

Навести на экран от которого нужно совершить переход, на нем появится круги и соединить с экраном на который нужно перейти.

![](img/img_connect.png)

### Обозначить стартовый экран

1) Выбрав стартовый экран 
2) Кликнуть правой кнопкой мыши
3) Выбрать Set as Start Destinition

![](img/img_start_destinition.png)

### Назначить "хост" навигационного графа

Для того чтобы сделать activity хостом нужно:

1) Добавить в layout activity NavHost
2) Сопоставить с графом

~~~ html
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <fragment
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        app:navGraph="@navigation/nav_graph"
        app:defaultNavHost="true" />

</FrameLayout>
~~~

app:defaultNavHost="true" - перехват системной кнопки Back.

3) Далее добавить в acitvity следующий код

~~~ Java
 @Override
 public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }
~~~

### Привязка переходов к виджетам

Есть два способа сделать это
~~~ Java
view.findViewById(R.id.btn_sign_up)
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment_sign_up, null));
                
view.findViewById(R.id.btn_sign_in)
                .setOnClickListener(v->{
                    Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_fragment_sign_up);
                });
~~~

Вот и все мы построили простейший навигационный граф

### Передача данных

1) Выбрав экран в navigation editor  добавляем аргументы

![](img/img_data.png)

2) Далее используем Bundle для передачи данных

~~~ Java
 Bundle bundle = new Bundle();
 bundle.putString("email", edEmail.getText().toString());
 bundle.putString("password", edPassword.getText().toString());
 Navigation.findNavController(view).navigate(R.id.fragmnet_congratulation, bundle);
~~~

3) Достаем данные через getArguments();

~~~ Java
getArguments().getString("email", "");
~~~
















