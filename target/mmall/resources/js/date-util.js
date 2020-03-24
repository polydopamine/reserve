function YYYYMMDDstart1()
{
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    //先给年下拉框赋内容
    var y  = new Date().getFullYear();
    for (var i = y; i < (y+30); i++) //以今年为准，前30年，后30年
        document.reg_testdate1.YYYY.options.add(new Option(" "+ i +" 年", i));

    //赋月份的下拉框
    for (var i = 1; i < 13; i++)
        document.reg_testdate1.MM.options.add(new Option(" " + i + " 月", i));

    //赋月份的下拉框
    for (var i = 0; i < 24; i++)
        document.reg_testdate1.HH.options.add(new Option(" " + i + " 时", i));

    //赋月份的下拉框
    for (var i = 0; i < 60; i++)
        document.reg_testdate1.MI.options.add(new Option(" " + i + " 分", i));

    document.reg_testdate1.YYYY.value = y;
    document.reg_testdate1.MM.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
    writeDay1(n); //赋日期下拉框Author:meizz
    document.reg_testdate1.DD.value = new Date().getDate();
}
if(document.attachEvent)
    window.attachEvent("onload", YYYYMMDDstart1);
else
    window.addEventListener('load', YYYYMMDDstart1, false);
function YYYYDD1(str) //年发生变化时日期发生变化(主要是判断闰平年)
{
    var MMvalue = document.reg_testdate1.MM.options[document.reg_testdate1.MM.selectedIndex].value;
    if (MMvalue == ""){ var e = document.reg_testdate1.DD; optionsClear(e); return;}
    var n = MonHead[MMvalue - 1];
    if (MMvalue ==2 && IsPinYear(str)) n++;
    writeDay1(n)
}
function MMDD1(str)   //月发生变化时日期联动
{
    var YYYYvalue = document.reg_testdate1.YYYY.options[document.reg_testdate1.YYYY.selectedIndex].value;
    if (YYYYvalue == ""){ var e = document.reg_testdate1.DD; optionsClear(e); return;}
    var n = MonHead[str - 1];
    if (str ==2 && IsPinYear(YYYYvalue)) n++;
    writeDay1(n)
}
function writeDay1(n)   //据条件写日期的下拉框
{
    var e = document.reg_testdate1.DD; optionsClear(e);
    for (var i=1; i<(n+1); i++)
        e.options.add(new Option(" "+ i + " 日", i));
}
function IsPinYear(year)//判断是否闰平年
{     return(0 == year%4 && (year%100 !=0 || year%400 == 0));}
function optionsClear(e)
{
    e.options.length = 1;
}



function YYYYMMDDstart2()
{
    MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    //先给年下拉框赋内容
    var y  = new Date().getFullYear();
    for (var i = y; i < (y+30); i++) //以今年为准，前30年，后30年
        document.reg_testdate2.YYYY.options.add(new Option(" "+ i +" 年", i));

    //赋月份的下拉框
    for (var i = 1; i < 13; i++)
        document.reg_testdate2.MM.options.add(new Option(" " + i + " 月", i));

    //赋月份的下拉框
    for (var i = 0; i < 24; i++)
        document.reg_testdate2.HH.options.add(new Option(" " + i + " 时", i));

    //赋月份的下拉框
    for (var i = 0; i < 60; i++)
        document.reg_testdate2.MI.options.add(new Option(" " + i + " 分", i));

    document.reg_testdate2.YYYY.value = y;
    document.reg_testdate2.MM.value = new Date().getMonth() + 1;
    var n = MonHead[new Date().getMonth()];
    if (new Date().getMonth() ==1 && IsPinYear(YYYYvalue)) n++;
    writeDay2(n); //赋日期下拉框Author:meizz
    document.reg_testdate2.DD.value = new Date().getDate();
}
if(document.attachEvent)
    window.attachEvent("onload", YYYYMMDDstart2);
else
    window.addEventListener('load', YYYYMMDDstart2, false);
function YYYYDD2(str) //年发生变化时日期发生变化(主要是判断闰平年)
{
    var MMvalue = document.reg_testdate2.MM.options[document.reg_testdate2.MM.selectedIndex].value;
    if (MMvalue == ""){ var e = document.reg_testdate2.DD; optionsClear(e); return;}
    var n = MonHead[MMvalue - 1];
    if (MMvalue ==2 && IsPinYear(str)) n++;
    writeDay2(n)
}
function MMDD2(str)   //月发生变化时日期联动
{
    var YYYYvalue = document.reg_testdate2.YYYY.options[document.reg_testdate2.YYYY.selectedIndex].value;
    if (YYYYvalue == ""){ var e = document.reg_testdate2.DD; optionsClear(e); return;}
    var n = MonHead[str - 1];
    if (str ==2 && IsPinYear(YYYYvalue)) n++;
    writeDay2(n)
}
function writeDay2(n)   //据条件写日期的下拉框
{
    var e = document.reg_testdate2.DD; optionsClear(e);
    for (var i=1; i<(n+1); i++)
        e.options.add(new Option(" "+ i + " 日", i));
}