# currency-calculator
﻿**Практического задание java**

**Задание**

Необходимо разработать калькулятор валют, работающий из консоли.

**Функционал**

- Калькулятор должен уметь работать с двумя валютами — доллар и рубль — и позволять выполнять операции сложения и вычитания.
- Складывать и вычитать можно только значения в одной валюте.
- Должна быть реализована операция конвертации из одной валюты в другую по курсу, который задается во внешнем файле конфигурации
- Необходимо реализовать поддержку не только целых, но и дробных значений.

**Замечания**

- Значение в долларах обозначается символом $, расположенным перед числом (например, $57,75).
- Значение в рублях — символом "р", расположенным после числа (например, 57,75р).
- Операция конвертации долларов в рубли — toRubles($57,75), рублей в доллары — toDollars(57,75р).
- Разделитель целой и дробной части может быть любым на выбор разработчика.

**Пример консольного ввода**

toDollars(737р + toRubles($85,4)). Для данного ввода вывод должен быть (при курсе 60 рублей за доллар, например): $93,68 (итоговый результат выводимый (но не промежуточные!) округлить до сотых)
