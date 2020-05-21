package com.javadsh98.database.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.javadsh98.database.room.converter.Converter
import com.javadsh98.database.room.dao.FarmDao
import com.javadsh98.database.room.dao.TreeDao
import com.javadsh98.database.room.entity.Farm
import com.javadsh98.database.room.entity.Tree
import com.javadsh98.database.room.model.Humidity
import com.javadsh98.database.room.model.RainFall
import com.javadsh98.database.room.model.Temperature

@Database(entities = [Farm::class, Tree::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converter::class])
abstract class MyDataBase() : RoomDatabase() {

    abstract fun getFarmDao(): FarmDao
    abstract fun getTreeDao(): TreeDao

    companion object {

        private var instance: MyDataBase? = null

        fun getInstance(application: Application): MyDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(application, MyDataBase::class.java, "farm_db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            ioThread {
                                FARM_DATA.map { instance!!.getFarmDao().insert(it) }
                                TREE_DATA.map { instance!!.getTreeDao().insert(it) }
                            }
                        }
                    }).build()
            }
            return instance!!
        }
    }

}

private val FARM_DATA = listOf(
    Farm(
        0,
        "برنج"
        ,
        listOf(
            RainFall("بیش از   1000   میلیمتر\n", "شرایط به جهت بارندگی   نسبتا مناسب   است\n")
            , RainFall("600 – 1000   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   است\n")
            , RainFall("کمتر از   600   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   نیست\n")
        )
        ,
        listOf(
            Temperature("بیش از   40   درجه سانتیگراد\n", "شرایط به جهت دمایی   مناسب   نیست\n")
            ,
            Temperature(
                "بین   33   تا   40   درجه سانتیگراد\n",
                "شرایط به جهت دمایی   به طور نسبی مناسب   است\n"
            )
            ,
            Temperature(
                "بین   22   تا   33   درجه سانتیگراد\n",
                "شرایط به جهت دمایی   مناسب   است\n"
            )
            ,
            Temperature("کمتر از   22   درجه   سانتیگراد\n", "شرایط به جهت دمایی   مناسب   نیست\n")
        )

        ,
        listOf(
            Humidity("بیش از   %90\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
            , Humidity("بین   %70   تا   %90\n", "شرایط به جهت رطوبتی   مناسب   است\n")
            , Humidity("بین   %40   تا   %70\n", "شرایط به جهت رطوبتی   به طور نسبی مناسب   است\n")
            , Humidity("کمتر از   %40\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
        )

        ,
        "برنج گیاهی است ویژه کشت در مناطق گرم و باتلاقی و کلاً گیاهی نیمه گرمسیری است. این گیاه در طول دوره رشد به آب فراوان نیاز دارد که در حدود ۳۰۰۰۰ متر مکعب در هکتار است. بنابراین باید در مناطقی که بارندگی در آن به اندازه کافی وجود داشته باشد کشت شود. در مناطقی که بارندگی آنها در حدود ۱۰۰۰ میلی\u200Cمتر باشد کشت برنج امکان\u200Cپذیر بوده و محصول خوبی بدست می\u200C\u200Cآید. چنانچه بارندگی از این مقدار کمتر باشد رشد برنج دچار اختلال خواهد شد. وجود سرما نیز باعث توقف رشد برنج خواهد شد و تولید محصول را پایین می\u200Cآورد. متوسط دمای مورد نیاز برنج حدود ۳۳ درجه سانتی\u200Cگراد است که این رقم در مورد ارقام زودرس کمتر و در مورد ارقام دیررس بیشتر است و ممکن است به ۴۰ درجه سانتی\u200Cگراد و یا حتی بیشتر نیز برسد. البته گاهی اوقات نیز بارندگی مصادف با زمان تلقیح برنج بوده که در این صورت شدیداً تأثیر منفی روی تولید محصول خواهد داشت. همچنین گاهی اوقات نیز بارندگی\u200Cهای شدید و مداوم در زمان برداشت برنج انجام می\u200Cگیرد، که سبب ورس محصول شده و برداشت آنرا با مشکل روبرو می\u200Cسازد.\n"
    )
    , Farm(
        0,
        "گندم"

        ,
        listOf(
            RainFall("بیش از   700   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   نیست\n")
            , RainFall("500 – 700   میلیمتر\n", "شرایط به جهت بارندگی   نسبتا مناسب   است\n")
            , RainFall("200 – 500   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   است\n")
            , RainFall("کمتر از   200   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   نیست\n")
        )

        ,
        listOf(
            Temperature("بیش از   20 درجه سانتیگراد\n", "شرایط به جهت دمایی   مناسب   نیست\n")
            ,
            Temperature(
                "بین   17   تا   20   درجه سانتیگراد\n",
                "شرایط به جهت دمایی   مناسب   است\n"
            )
            ,
            Temperature("کمتر از   17   درجه   سانتیگراد\n", "شرایط به جهت دمایی   مناسب   نیست\n")
        )

        ,
        listOf(
            Humidity("بیش از   %70\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
            , Humidity("بین   %50   تا   %70\n", "شرایط به جهت رطوبتی   مناسب   است\n")
            , Humidity("بین   %25   تا   %50\n", "شرایط به جهت رطوبتی   به طور نسبی مناسب   است\n")
            , Humidity("کمتر از   %25\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
        )

        ,
        "شرایط ایده آل برای رشد گندم، آب و هوای خنک در دوره رشد رویشی، آب و هوای معتدل در دوران تشکیل دانه و آب و هوای گرم و خشک در زمان برداشت محصول می باشد. بنابراین در مناطقی که زمستان های سخت دارند، کشت گندم امشکلاتی از قبیل سرمازدگی زمستانی مواجه می شود. البته باید بدانیم که گندم در برابر خشکی مقاومت چندانی ندارد و نمی تواند به مدت طولانی، خشکی و کم آبی را تحمل نماید. اما قادر است خود را با شرایط خشک تا حدی تطبیق داده و با تشکیل یاخته های کوچکتر که در نهایت سبب تشکیل برگ های کوچک شده و در نتیجه روزنه ها کوچک تر می شود. سطح تعریق را کاهش دهد و از اثرات سوء کم آبی تا حدی محفوظ بماند. این گیاه قادر به رشد در محدوده وسیعی از انواع آب و هوا است. بیشترین مقدار گندم جهان در مناطق نیمه خشک (با بارندگی سالانه ۲۵۰-۵۰۰ میلی متر) و نیمه مرطوب (با بارندگی۷۰۰ -۵۰۰ میلی متر) به عمل می آید. البته مقداری هم در مناطق خشک با بارندگی حدود ۲۰۰ میلیمتر تولید می شود. چون گندم سازگاری زیادی با رطوبت و دمای بالا ندارد. در مناطق گرمسیری و نیمه گرمسیری در ارتفاعات و در مواقع سرد سال کشت می شود. مناسب ترین شرایط برای رشد رضایت بخش گندم، آب و هوای مرطوب و سرد زمستان است که هوایی گرم، خشک و صاف به مدت ۶ إلى ۸ هفته، در طول دوره رسیدگی با میانگین حرارت ۱۸-۱۹ درجه سانتی گراد را به دنبال دارد. این که چگونه پس از سه هفته گیاهان متکی به تک ریشه قادر به حفظ و نگهداری آب بوده اند، کمی مبهم است.بعضی از محققین اظهار نظر کرده اند که چگونگی توسعه ریشه در واریته های مختلف گندم، بر روی میزان مقاومت آن ها به خشکی مؤثر است. در یک آزمایش، گندم را در سه مرحله پنجه زنی خوشه رفتن و دانه بستن در معرض تنش رطوبت قرار دادند. این گیاهان در اتاقک های رشد به طور شبانه روزی تحت دماهای ۱۰ . ۱۸ و ۲۷ درجه سانتی گراد قرار گرفتند. نتایج نشان داد که در مرحله پنجه زنی گیاه به دلیل در بر گرفتن حجم زیاد خاک در مقایسه با سطح سبز برگ تنش به سرعت رخ نداد. در مرحله پنجه زنی تنش رطوبت باعث کاهش فتوسنتز و پتانسیل آب برگ و افزایش مقاومت روزنه ها شد.\n"
    )
    , Farm(
        0,
        "توت فرنگی"

        ,
        listOf(
            RainFall("بیش از   1200   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   نیست\n")
            , RainFall("800 – 1200   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   است\n")
            , RainFall("کمتر از   800   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   نیست\n")
        )

        ,
        listOf(
            Temperature("بیش از   26   درجه سانتیگراد\n", "شرایط به جهت دمایی   مناسب   نیست\n")
            ,
            Temperature("بین   18   تا   26   درجه سانتیگراد\n", "شرایط به جهت دمایی مناسب   است\n")
            ,
            Temperature(
                "بین   14   تا   18   درجه سانتیگراد\n",
                "شرایط به جهت دمایی   به طور نسبی  مناسب   است\n"
            )
            ,
            Temperature("کمتر از   14   درجه   سانتیگراد\n", "شرایط به جهت دمایی   مناسب   نیست\n")
        )

        ,
        listOf(
            Humidity("بیش از   %85\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
            , Humidity("بین   %55   تا   %85\n", "شرایط به جهت رطوبتی   مناسب   است\n")
            , Humidity("کمتر از   %55\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
        )

        ,
        "توت فرنگی یک گیاه علفی دائمی است که دارای یک ساقه ی مرکزی یا طوقه بوده و برگھا، ریشه ھا، ساقه و گل آذین ھا از آن منشأ می گیرند. در طول طوقه در بالای ھر برگ یک جوانه ی محوری (Runners ) ھای رونده وجود دارد که بسته به شرایط محیطی می تواند ساقه ھای رونده و طوقه ھای دیگر را تولید نموده و یا به حالت رکود( خواب) باقی بماند. برگھا به صورت مارپیچی در روی ساقه قرار می گیرند به طوری که ششمین برگ در امتداد برگ اول قرار می گیرد. برگھا معمولا شًانه ای وسه برگچه ای ھستند و دارای اپیدرم، نرده چوبی( و لایه ھای مزوفیل، مشابه سایر دو لپه ای می باشند.برگھای اکثر گونه ھا فقط به مدت کوتاھی( ( Palisade حدود چند ماه) زنده بوده و پس از قرار گرفتن در معرض یخبندان سخت پائیزه، از بین می روند. . ریشه ھا از قسمت بیرونی و تحتانی طوقه، جایی که با خاک در تماس است پدیدار می شوند. ریخت شناسی ریشه، مانند دو لپه ای ھا است. ریشه ھا ی نا بجای طوقه از پریسکل منشاً یافته، رشد می کنند و به طور مستقیم از کورتکس بیرون می آیند. ریشه ھایی که به طول ٢ تا ۵ سانتی متر می رسند شروع به منشعب شدن می کنند و چنانچه آب کافی دردسترس آنھا قرار گیرد، منشعب شدن ریشه ادامه یافته و منجر به تشکیل یک توده فیبری می شود. گل آذین توت فرنگی، یک ساقه ی تغییر شکل یافته است که به یک شکوفه ی اولیه ختم می شود. شکوفه دارای ١٠ کاسبرگ، ۵ گلبرگ و ٢٠ الی ٣٠ پرچم است و تعداد تخمدانھا از ۶٠ تا ۶٠٠ عدد متغیر است. . عمل گرده افشانی در توت فرنگی به وسیله ی حشرات صورت می گیرد. در این بین، زنبور عسل بیشترین نقش را ایفاء می نماید. در واقع این گیاه خود گرده افشان است ولی وجود زنبور عسل برای گرده افشانی ترجیح داده می شود. توت فرنگی یک میوه ی مجتمع بوده و شامل مادگی ھای فراوان است که ھر کدام شامل یک تخمک می باشند. بذور حاصل آکن (فندقه) نامیده شده و میوه ی حقیقی توت فرنگی می باشد.\n"
    )
)

private val TREE_DATA = listOf(
    Tree(
        0,
        "درخت نارنج"
        ,
        listOf(
            RainFall("بیش از   700   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   است\n")
            , RainFall("500 – 700   میلیمتر\n", "شرایط به جهت بارندگی   نسبتا مناسب   است\n")
            , RainFall("کمتر از   500   میلیمتر\n", "شرایط به جهت بارندگی   مناسب   نیست\n")
        )
        ,
        listOf(
            Temperature(
                "بیش از   28   درجه سانتیگراد\n",
                "شرایط به جهت دمایی   نسبتا مناسب   نیست\n"
            )
            ,
            Temperature("بین   25   تا   28   درجه سانتیگراد\n", "شرایط به جهت دمایی مناسب   است\n")
            ,
            Temperature(
                "کمتر از   25   درجه   سانتیگراد\n",
                "شرایط به جهت دمایی   نسبتا مناسب   نیست\n"
            )
        )
        ,
        listOf(
            Humidity("بیش از   %70\n", "شرایط به جهت رطوبتی   مناسب   است\n")
            , Humidity("کمتر از   %70\n", "شرایط به جهت رطوبتی   مناسب   نیست\n")
        )
        ,
        "مرکبات در شرایط گرمسیر (همانند مناطق مرکزی و جنوب ایران) و نیمه گرمسیری (شمال ایران) قابلیت رشد داشته که در این مناطق شروع رشد این گیاه (در بهار) 5/12 درجه سانتیگراد و مناسبترین دما برای حداکثر رشد 25 تا 28 درجه سانتیگراد می باشد. خاکهای سبک شنی رسی با pH 5 تا 7 عموما برای توسعه کشت مرکبات مناسب می باشند. با توجه به موارد ذکر شده در شمال ایران خطر سرما و بالا بودن سطح آب زیرزمینی و در جنوب مشکل گرما، شوری و کم آبی وجود داشته که در توسعه کشت و احداث باغات مرکبات باید مورد توجه قرار داد. واکنش درختان میوه به عوامل محیطی ، به دلیل چند ساله بودن و نحوه رشد و باروری آنها ، تا حدودی با سایر گیاهان متفاوت است . از آنجا که درختان مرکبات ، پس از بالغ شدن ، حجم زیادی پیدا می\u200Cکنند و باید با فواصل به نسبت زیادی از یکدیگر کشت شوند ، تغییر دادن عوامل تشکیل\u200Cدهنده محیط رشد و مناسب کردن آنها برای گیاهان کاشته اغلب اگر ناممکن نباشد ، بسیار دشوار است . در نتیجه ، باید کوشش شود تا محیط کشت این گیاهان از ابتدا مناسب باشد .\n" +
                "نواحی مرکبات خیز ایران:\n" +
                "الف- سواحل دریای مازندران:\n" +
                "از آستارا تا گرگان ارقام مختلفی از مرکبات کشت می شود. محدودیتهای اقلیمی (سرما و خشکی) موجب گردیده تا بیشتر تراکم باغهای مرکبات در نواحی چابکسر تا بهشهر متمرکز باشند.\n" +
                "ب- ناحیه مرکزی:\n" +
                "این ناحیه شامل شهرهایی مرکزی ایران همچون فارس، کرمان، جیرفت، بم و غیره بوده که بیشتر دارای روزهای آفتابی به همراه رطوبت نسبی پایین است. بارندگی این نواحی پایین بوده، لذا در تابستان نیاز به چندین نوبت آبیاری بوده تا گیاه عملکرد و کیفیت محصول مناسبی داشته باشد.\n" +
                "ج- نواحی ساحلی جنوب (بندرعباس و سواحل دریای عمان):\n" +
                "این نواحی عاری از یخبندان، با رطوبت نسبی بالا و گرمای زیاد در تابستان می باشد. در این ناحیه لایم از کیفیت خوبی برخوردار است.\n"
    )
)