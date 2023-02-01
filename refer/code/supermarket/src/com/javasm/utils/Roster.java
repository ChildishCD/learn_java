package com.javasm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Roster {
    private Roster(){}
    private static ArrayList<String> nameList = new ArrayList<>();
    private static ArrayList<String> fruitsList = new ArrayList<>();
    static {
        StringBuilder  sb = new StringBuilder();
        sb.append("小明,小红,小黑,小绿,小紫,小粉,小强,张三,李四");
        sb.append(",");
        sb.append("刀白凤,丁春秋,马夫人,马五德,小翠,于光豪,巴天石,不平道人,邓百川,风波恶,甘宝宝,公冶乾,木婉清,包不同,天狼子,王语嫣,乌老大,无崖子,云岛主,云中鹤,止清,白世镜,天山童姥,本参,本观,本相,本因,出尘子,冯阿三,古笃诚,少林老僧,过彦之,兰剑,平婆婆,石清露,石嫂,司空玄,司马林,玄慈,玄寂,玄苦,玄难,玄生,玄痛,叶二娘,左子穆,耶律莫哥,李春来,李傀儡,李秋水,刘竹庄,祁六三,乔峰,全冠清,朴者和尚,阮星竹,许卓诚,朱丹臣,竹剑,阿碧,阿洪,阿胜,西夏宫女,阿朱,阿紫,波罗星,陈孤雁,何望海,鸠摩智,来福儿,耶律洪基,努儿海,宋长老,苏星河,苏辙,完颜阿古打,吴长风,枯荣长老,辛双清,严妈妈,余婆婆,岳老三,张全祥,单伯山,单季山,单叔山,单小山,单正,段延庆,段誉,段正淳,段正明,范禹,宗赞王子,范骅,苟读,和里布,孟师叔,华赫艮,耶律涅鲁古,耶律重元,郁光标,卓不凡,范百龄,哈大霸,姜师叔,吴光胜,梦姑,神山上人,神音,狮鼻子,室里,项长老,姚伯当,幽草,赵钱孙,黄眉和尚,哲罗星,钟灵,钟万仇,高升泰,龚光杰,贾老者,康广陵,秦红棉,容子矩,桑土公,唐光雄,奚长老,徐长老,诸保昆,崔百泉,崔绿华,符敏仪,赵洵,菊剑,梅剑,萧远山,虚竹,游骥,聋哑婆婆,游驹,游坦之,程青霜,傅思归,葛光佩,缘根,鲍千灵,智光大师,褚万里,瑞婆婆,端木元,黎夫人,慕容博,慕容复,谭公,赫连铁树,谭婆,谭青,摘星子,慧方,慧观,慧净,慧真,穆贵妃,薛慕华,吴领军,易大彪");
        sb.append(",");
        sb.append("马青雄,马钰,小沙弥,木华黎,丘处机,沈青刚,书记,一灯大师,书生,王处一,王罕,尹志平,包惜弱,冯衡,术赤,天竺僧人,孙不二,札木合,华筝,李萍,刘玄处,刘瑛姑,吕文德,完颜洪烈,乔寨主,曲三,曲傻姑,全金发,汤祖德,朱聪,陈玄风,完颜洪熙,陆乘风,陆冠英,沙通天,农夫,吴青烈,赤老温,杨康,灵智上人,杨铁心,余兆兴,张阿生,张十五,忽都虎,欧阳峰,欧阳克,拖雷,者勒米,周伯通,段天德,郭靖,郭啸天,郝大通,洪七公,侯通海,姜文,柯镇恶,枯木,南希仁,胖妇人,胖丐,胖子,哑梢公,都史,钱青健,桑昆,铁木真,盖运聪,黄蓉,黄药师,梁长老,梁子翁,梅超风,渔人,博尔忽,博尔术,程瑶迦,韩宝驹,韩小莹,鲁有脚,穆念慈,彭长老,彭连虎,童子,窝阔台,简长老,焦木和尚,简管家,裘千仞,裘千丈,瘦丐,察合台,谭处端,黎生,樵子");
        sb.append(",");
        sb.append("卜沉,丁坚,丁勉,上官云,万大平,于人豪,于嫂,不戒和尚,长青子,仇松年,丹青生,邓八公,方人智,方生,方证,天门道人,计无施,木高峰,风清扬,丛不弃,王伯奋,王诚,王二叔,天乙道人,王夫人,王家驹,王家骏,王元霸,王仲强,白二,白熊,天松道人,乐厚,令狐冲,宁中则,平夫人,平一指,申人俊,史镖头,东方不败,史登达,司马大,田伯光,仪和,仪琳,仪清,玉玑子,玉灵道人,玉磬子,玉音子,玉钟子,左冷禅,成不忧,齐堂主,吉人通,冲虚道长,老不死,老头子,刘菁,刘芹,刘正风,米为义,农妇,成高道人,曲非烟,曲洋,任我行,英颚,西宝,向大年,向问天,陈七,陈歪嘴,迟百诚,狄镖头,狄修,杜长老,何三七,季镖头,定静师太,劳德诺,陆伯,陆大有,任盈盈,沙天江,秃笔翁,吴柏英,吴天德,辛国梁,严三星,杨莲亭,余沧海,余人彦,岳灵珊,张夫人,张金鏊,定逸,建除,林平之,林远图,林震南,罗人杰,易国梓,易师爷,易堂主,英白罗,英长老,岳不群,郑镖头,郑萼,周孤桐,费彬,封不平,洪人雄,侯人英,觉月,施戴子,施令威,闻先生,哑婆婆,钟镇,祝镖头,祖千秋,高克新,高明根,贾布,贾人达,夏老拳师,秦娟,秦伟帮,桑三娘,桃干仙,桃根仙,桃花仙,桃实仙,桃叶仙,桃枝仙,陶钧,莫大,崔镖头,黄伯流,黄国柏,黄钟公,梁发,绿竹翁,游迅,葛长老,震山子,黑白子,黑熊,麻衣汉子,鲁连荣,舒奇,童百熊,鲍大楚,解风,蓝凤凰,谭迪人,清虚道人");
        sb.append(",");
        sb.append("子聪,丁大全,人厨子,九死生,马钰,小棒头,大头鬼,一灯大师,马光佐,小龙女,尹志平,丘处机,王处一,王十三,公孙止,小王将军,王志坦,王惟忠,无常鬼,尹克西,天竺僧,少妇,孙婆婆,公孙绿萼,孙不二,皮清云,申志凡,冯默风,讨债鬼,史伯威,史仲猛,史叔刚,史季强,史孟龙,尼摩星,李莫愁,达尔巴,刘处玄,朱子柳,圣因师太,曲傻姑,吕文德,祁志诚,李志常,刘瑛姑,吊死鬼,百草仙,陆鼎立,陆二娘,阿根,张志光,完颜萍,陆冠英,宋德方,陈大方,觉远大师,沙通天,张君宝,张一氓,陈老丐,张二叔,陆无双,杨过,灵智上人,武三通,武敦儒,武修文,武三娘,林朝英,耶律晋,耶律燕,耶律楚材,忽必烈,丧门鬼,狗头陀,青灵子,欧阳峰,耶律齐,周伯通,金轮法王,洪凌波,柔儿,郭破虏,侯通海,宋五,俏鬼,柯镇恶,点苍渔隐,赵志敬,洪七公,郭靖,郭芙,郭襄,姬清玄,笑脸鬼,鹿清笃,崔志方,鄂尔多,萨多,黄药师,黄蓉,程遥迦,鲁有脚,彭连虎,韩无垢,童大海,韩老丐,彭长老,瘦丐,程瑛,雷猛,裘千尺,蒙哥,煞神鬼,催命鬼,蓝天和,裘千仞,赫大通,潇湘子,霍都,樊一翁,藏边大丑,藏边二丑,藏边三丑,藏边四丑,藏边五丑");
        sb.append(",");
        sb.append("卜泰,丁敏尹,马法通,卫天望,卫四娘,小翠,小虹,乌旺阿普,小凤,小昭,卫璧,王难姑,元广波,邓愈,方天劳,天鸣方丈,韦一笑,云鹤,无色,王八衰,方东白,五姑,贝锦仪,无相禅师,小玲　,王保保,太虚子,史镖头,史火龙,史红石,平等宝树王,叶长青,孙三毁,白龟寿,陈友谅,冷谦,汤和,朱元璋,传功长老,祁天彪,纪晓芙,朱长龄,西华子,刘敖,阳顶天,齐心宝树王,李四摧,过三拳,李天恒,刚相,朱九真,乔福,苏梦清,司徒千钟,季长老,花云,吴良,吴祯,张无忌,麦鲸,何足道,执法长老,杜百当,杨逍,辛然,妙风使,邵鹤,邵燕,吴劲草,寿南山,吴六破,张中,何太冲,孟正鸿,灵虚,宋青书,张三丰,阿二,阿三,杨不悔,杨姐姐,宋远桥,张松溪,张翠山,苏习之,麦少帮主,周芷若,郑长老,宗维侠,范遥,拨速台,周颠,空闻,空智,空性,空见,空性,周五输,郑七灭,明月,武青婴,金花婆婆,武烈,易三娘,说不得,胡青牛,泉建男,郝密,闻苍松,哈总管,觉远,赵敏,赵一伤,封坛主,贺老三,郭襄,宫九佳　欧阳牧之,姚清泉,胡青羊,俞连舟,俞岱岩,都大锦,徐达,唐洋,高老者,圆真（成昆）,唐文亮,高则成,流云使,俱明宝树王,夏胄,圆业,圆心,钱二败,殷野王,殷天正,殷无禄,殷无福,殷无寿,殷离,班淑娴,殷素素,殷梨亭,莫声谷,常敬之,常遇春,常胜宝树王,渡劫,渡难,渡厄,常金鹏,清风,鹿杖客,韩林儿,掌棒龙头,彭莹玉,谢逊,蒋涛,辉月使,程坛主,韩千叶,矮老者　掌钵龙头,简捷,詹春,静空,静照,静迦,静慧,庄铮,秦老五,察罕特穆尔,鲜于通,德成,潘天耕,颜恒,鹤笔翁　薛公远,圆音,静虚师太,静玄师太,摩诃巴思,灭绝师太");
        Collections.addAll(nameList,sb.toString().split(","));
        sb = new StringBuilder();
        sb.append("苹果,沙果,海棠,野樱莓,枇杷,欧楂,山楂,杏,樱桃,桃,李子,梅子,橘子,砂糖桔,橙子,柠檬,青柠,柚子,金桔,葡萄柚,香橼,佛手,指橙,黄皮果,哈密瓜,香瓜,白兰瓜,刺角瓜,金铃子,草莓,菠萝莓,黑莓,覆盆子,云莓,罗甘莓,白里叶莓,葡萄,提子,醋栗,黑醋栗,红醋栗,蓝莓,蔓越莓,越橘,乌饭果,柿子,黑枣,香蕉,大蕉,南洋红香,无花果,菠萝蜜,构树果实,牛奶果,桑葚,火龙果,黄龙果,红心火龙果,仙人掌果,荔枝,龙眼,红毛丹,榴莲,猴面包果,阳桃,三敛果,椰子,槟榔,海枣,蛇皮果,莲雾,嘉宝果,番石榴,菲油果,苏里南苦樱桃,枸杞,香瓜茄,灯笼果,圣女果,芒果,山竹,柑橘,莲子,橄榄");
        Collections.addAll(fruitsList,sb.toString().split(","));
    }
    public static String getName(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return nameList.remove(random.nextInt(nameList.size()));
    }
    public static String getFruitsName(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return fruitsList.remove(random.nextInt(fruitsList.size()));
    }
}
