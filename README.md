# The Navigation Architecture Component

## Вступление

Построение навигации в android приложении всегда была проблемой многих разработчиков. Сообщество написало много библиотек которые в какой-то мере решали проблемы, но многие ждали решение именно от Google. На Google IO 2018 анонсируют Navigation Architecture Component которое входит в состав Jetpack. На данный момент данная технология находится в разработке, но уже доступна в Android Studio 3.2 Beta.

## Установка и первоначальная настройка проекта

Добавим необходимые зависимости в 'build/gradle'

### Основные библиотеки
~~~ groovy
dependencies {
    implementation "android.arch.navigation:navigation-fragment:1.0.0-alpha05"
    implementation "android.arch.navigation:navigation-ui:1.0.0-alpha05"
}
~~~
### Ресурсы
В папке res нужно создать ресурсы для навигации New -> Android resource file
и выбрать Resource type Navigation, укажите имя файла, кликнете OK.

![](img/img_create_nav.png)

Создается новая директория navigation в которой будет находиться ранее созданный файл.

## Обзор начального функционала 

Файл навигационного графа имеет два режима разработки editor и text

### Navigation Editor

Предназначен для разработки навигации при помощи ui без написания кода.

![](img/img1.png)

Имеет три столбца

1) Destination - отображает все экраны в графе
2) UI  отображение графа
3) Attributes - редактор атрибутов графа

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

Библиотека navigation предназначена для реализации “single activity application” поэтому activity выступает в роли "хоста" графа.

Перейдем к практике и напишем простой навигационный граф постепенно усложняя его.

### Добавление экранов

1) В редакторе графов кликните New Destination . Откроется диалоговое окно New Destination.
2) Кликните Create blank destination или выберете существующий фрагмент или активность. Откроется диалоговое окно Android Component.
3) Введите имя в поле Fragment Name. 
4) Введите имя в поле Fragment Layout Name. Это имя будет присвоено layout файлу фрагмента.
5) Нажмите Finish. 

![](img/img_create.png)

### Соединение экранов

Навести на экран на нем появится круг и соединить с экраном на который нужно перейти.

![](img/img_connect.png)

При это в xml файле генерируется action который указывает на следующий fragment

~~~ html
<fragment
        android:id="@+id/fragment_prev"
        android:name="andrey.murzin.navigation.FragmentMain"
        android:label="fragment_main"
        tools:layout="@layout/fragment_main" >

        <action
            android:id="@+id/action_fragment_prev_to_fragment_next"
            app:destination="@id/fragment_next" />
   
</fragment>
~~~

Если нам нужно сделать Splash screen то нужно добавить параметр app:clearTask="true" это очистит весь предыдущий стэк.

### Обозначить стартовый экран

1) Выбрав стартовый экран 
2) Кликнуть правой кнопкой мыши
3) Выбрать Set as Start Destinition

![](img/img_start_destinition.png)

либо добавить в navigation в параметры app:startDestination свой fragment

~~~ html
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/fragment_start"/>
~~~

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

Чтобы привязать переход из одного экрана в другой к определенному событию (клик по кнопки и тд.)

Для этого есть два способа

1) Нужно передать view в findNavController и выбрать нужный action id.

~~~ Java
view.findViewById(R.id.btn_sign_in)
                .setOnClickListener(v->{
                    Navigation.findNavController(v).navigate(R.id.action_fragment_main_to_fragment_sign_up);
                });
~~~

2) Немного упрощенная версия этого же действия метод createNavigateOnClickListener возвращает готовый listner и его нужно передать в нужное вам view, только теперь нужно указывать не action id, а fragment id на который хотим перейти.
~~~ Java
view.findViewById(R.id.btn_sign_up)
                .setOnClickListener(Navigation.createNavigateOnClickListener(R.id.fragment_sign_up, null));
~~~
Использовать можно и тот и тот явных плюсов и минусов я не выделил
По сути это второй способ просто обертка первого способа вот так выглядит метод createNavigateOnClickListener изнутри 

~~~ Java
@NonNull
    public static View.OnClickListener createNavigateOnClickListener(@IdRes final int resId,
            @Nullable final Bundle args) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(resId, args);
            }
        };
    }
~~~

### Навигация между Activity

Библиотека navigation придерживается концепции "single activity application" вы не можете построить навигацию такого вида activity->actvivity. Вы можете реализовать только fragment->activity и в activity будет один экран без дальнейшей навигации, либо fragment->activity(graph) то есть в activity будет еще один граф. Навигация на activity реализуется таким же  образом, как и на fragment.

Нужно повторить пункты для activty на который вы осуществляете переход:

1) Создание графа навигации
2) Добавление экранов
3) Обозначить стартовый экран
4) Назначить "хост" навигационного графа

Я думаю нужно избегать использование множество activity чтобы не противоречить концепции библиотеки navigation так как тут явно появляются проблемы с тем что после того как мы перешли в activity с еще одним графом, мы не видим его внутренний граф и полная картина навигации становится не так очевидна.

Для это задачи есть более изящное решение это вложенный граф.

### Вложенный граф
Вложенный граф делается очень легко, нужно только выбрать экран с которого будет начинаться вложенный граф и установить для него новый граф.

![](img/img_nested.png)

По итогу наш граф будет выглядеть

![](img/nested_graph.png)

И теперь мы можем переходить во вложенный граф из главного. При этом наглядно видно куда мы переходим, и что это не просто экран, а вложенный граф. Кстати новый xml файл не создается, fragments просто заключаются в navigation тэг.

~~~ Java
<navigation android:id="@+id/navigation"
        app:startDestination="@id/fragmentNested">
         <fragment
            android:id="@+id/fragmentNested"
            android:name="test.FragmentNested"
            android:label="fragment_nested"
            tools:layout="@layout/fragment_nested" >
</navigation>
~~~


### Передача данных

1) Далее используем Bundle для передачи данных

~~~ Java
 Bundle bundle = new Bundle();
 bundle.putString("email", edEmail.getText().toString());
 bundle.putString("password", edPassword.getText().toString());
 Navigation.findNavController(view).navigate(R.id.fragmnet_congratulation, bundle);
~~~

2) Достаем данные через getArguments();

~~~ Java
getArguments().getString("email", "");
~~~

Способ не отличается от того как мы передавали данные ранее.

Минусы

1) Ключи для передачи данных нужно объявлять как публичные константы
2) Постоянно нужно проверять куда передаешь и какого типа, и где это используется

Плюсы

1) Мало кода
2) Нового ничего учить не надо

### Передача данных safetype

Есть второй способ передавать данные при помощи type-safe. Этот способ более безопасный. Генерируется два файла обертки Direction для передачи данных и Args для извлечения данных у него есть getter методы как у простой модели. 

Чтобы передавать данные этим способом нужно настроить проект

1) Добавить плагин

~~~ groovy
apply plugin: 'com.android.application'
apply plugin: 'androidx.navigation.safeargs'
~~~

2) Добавить в build.gradle зависимость

~~~ groovy
dependencies {
        classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-alpha02"
    }
~~~

3) Выбрав экран в navigation editor  добавляем аргументы

![](img/img_data.png)

В данный момент так сделать не получится потому что type deprecated и проект не соберётся, поэтому напишем руками в Text передаваемые аргументы, указывая тип argType, так же можно указать defaultValue.

~~~ html
    <fragment
        android:id="@+id/fragmnet_congratulation"
        android:name="andrey.murzin.navigation.FragmnetCongratulation"
        android:label="fragment_congratulation"
        tools:layout="@layout/fragment_congratulation" >
        <argument
            android:name="email"
            app:argType="string" />
        <argument
            android:name="password"
            app:argType="string" />
    </fragment>
~~~

4) Передача данных
~~~ java
FragmentSignUpDirections.Action_fragment_sign_up_to_fragmnet_congratulation action
                        = FragmentSignUpDirections
                        .action_fragment_sign_up_to_fragmnet_congratulation("email", "password");
Navigation.findNavController(view).navigate(action);
~~~

5) Извлекаем данные

~~~ java
FragmnetCongratulationArgs.fromBundle(getArguments()).getEmail();
~~~

Этот способ мне кажется более правильным и безопасным в использовании

Плюсы

1) В xml графа можно явно посмотреть что передается и куда.
2) Заполняя FragmentDiraction.Action видим данные которые требуются для передачи
3) Извлечение данных через getter защищает от ошибок

Минусы

1) Нужно писать больше кода

### Передача данных custom object

1) Создать модель которая реализует интерфейс Parcelable
2) Добавить аргумент argType должен соответствовать имени модели

~~~ html
<argument
    android:name="user"
    app:argType="User" />
~~~ 

### Анимация перехода между экранами

1) В Navigation Editor выбрать Action который будет осуществлять анимацию перехода
2) В панели Transaction в выпадающем меню выбрать анимацию в переходы и выхода

![](img/img_transition.png)

У Action есть четыре параметра для анимации. Чтобы понять какой параметр для чего нужен представим что у нас есть переход между A->B

app:enterAnim Определяет анимацию для фрагмента B при переходе в верхнюю часть стека.

app:popEnterAnim Анимационный переход для фрагмента A при заполнении верхней части стека.

app:exitAnim Определяет анимацию фрагмента A, переход к нижней части стека.

app:popExitAnim Анимационный переход фрагмента B, выходящего из стека.

## Итоги

В настоящее время библиотека находится на ранней альфа-версии. Поэтому использовать ее в боевых проектах пока рано, но несмотря на это Navigation Architecture Component очень гибкая, мощная и интуитивно понятная библиотека.
