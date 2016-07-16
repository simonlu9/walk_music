package org.music.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Area {
	public static HashMap<String, Integer> direct =  new HashMap<String, Integer>();
	public Area()  {
	//	HashMap<String, Integer> direct = 
		String ss = "[{id:1,name:\"直辖市\",cities:{11e4:\"北京市\",12e4:\"天津市\",31e4:\"上海市\",5e5:\"重庆市\"}},{id:2,name:\"特别行政区\",cities:{81e4:\"香港\",82e4:\"澳门\"}},{id:13e4,name:\"河北省\",alias:\"河北\",type:0,cities:{130100:\"石家庄市\",130200:\"唐山市\",130300:\"秦皇岛市\",130400:\"邯郸市\",130500:\"邢台市\",130600:\"保定市\",130700:\"张家口市\",130800:\"承德市\",130900:\"沧州市\",131e3:\"廊坊市\",131100:\"衡水市\"}},{id:14e4,name:\"山西省\",alias:\"山西\",type:0,cities:{140100:\"太原市\",140200:\"大同市\",140300:\"阳泉市\",140400:\"长治市\",140500:\"晋城市\",140600:\"朔州市\",140700:\"晋中市\",140800:\"运城市\",140900:\"忻州市\",141e3:\"临汾市\",141100:\"吕梁市\"}},{id:15e4,name:\"内蒙古\",alias:null,type:0,cities:{150100:\"呼和浩特市\",150200:\"包头市\",150300:\"乌海市\",150400:\"赤峰市\",150500:\"通辽市\",150600:\"鄂尔多斯市\",150700:\"呼伦贝尔市\",150800:\"巴彦淖尔市\",150900:\"乌兰察布市\",152200:\"兴安盟\",152500:\"锡林郭勒盟\",152900:\"阿拉善盟\"}},{id:21e4,name:\"辽宁省\",alias:\"辽宁\",type:0,cities:{210100:\"沈阳市\",210200:\"大连市\",210300:\"鞍山市\",210400:\"抚顺市\",210500:\"本溪市\",210600:\"丹东市\",210700:\"锦州市\",210800:\"营口市\",210900:\"阜新市\",211e3:\"辽阳市\",211100:\"盘锦市\",211200:\"铁岭市\",211300:\"朝阳市\",211400:\"葫芦岛市\"}},{id:22e4,name:\"吉林省\",alias:\"吉林\",type:0,cities:{220100:\"长春市\",220200:\"吉林市\",220300:\"四平市\",220400:\"辽源市\",220500:\"通化市\",220600:\"白山市\",220700:\"松原市\",220800:\"白城市\",222400:\"延边朝鲜族自治州\"}},{id:23e4,name:\"黑龙江省\",alias:\"黑龙江\",type:0,cities:{230100:\"哈尔滨市\",230200:\"齐齐哈尔市\",230300:\"鸡西市\",230400:\"鹤岗市\",230500:\"双鸭山市\",230600:\"大庆市\",230700:\"伊春市\",230800:\"佳木斯市\",230900:\"七台河市\",231e3:\"牡丹江市\",231100:\"黑河市\",231200:\"绥化市\",232700:\"大兴安岭地区\"}},{id:32e4,name:\"江苏省\",alias:\"江苏\",type:0,cities:{320100:\"南京市\",320200:\"无锡市\",320300:\"徐州市\",320400:\"常州市\",320500:\"苏州市\",320600:\"南通市\",320700:\"连云港市\",320800:\"淮安市\",320900:\"盐城市\",321e3:\"扬州市\",321100:\"镇江市\",321200:\"泰州市\",321300:\"宿迁市\"}},{id:33e4,name:\"浙江省\",alias:\"浙江\",type:0,cities:{330100:\"杭州市\",330200:\"宁波市\",330300:\"温州市\",330400:\"嘉兴市\",330500:\"湖州市\",330600:\"绍兴市\",330700:\"金华市\",330800:\"衢州市\",330900:\"舟山市\",331e3:\"台州市\",331100:\"丽水市\"}},{id:34e4,name:\"安徽省\",alias:\"安徽\",type:0,cities:{340100:\"合肥市\",340200:\"芜湖市\",340300:\"蚌埠市\",340400:\"淮南市\",340500:\"马鞍山市\",340600:\"淮北市\",340700:\"铜陵市\",340800:\"安庆市\",341e3:\"黄山市\",341100:\"滁州市\",341200:\"阜阳市\",341300:\"宿州市\",341400:\"巢湖市\",341500:\"六安市\",341600:\"亳州市\",341700:\"池州市\",341800:\"宣城市\"}},{id:35e4,name:\"福建省\",alias:\"福建\",type:0,cities:{350100:\"福州市\",350200:\"厦门市\",350300:\"莆田市\",350400:\"三明市\",350500:\"泉州市\",350600:\"漳州市\",350700:\"南平市\",350800:\"龙岩市\",350900:\"宁德市\"}},{id:36e4,name:\"江西省\",alias:\"江西\",type:0,cities:{360100:\"南昌市\",360200:\"景德镇市\",360300:\"萍乡市\",360400:\"九江市\",360500:\"新余市\",360600:\"鹰潭市\",360700:\"赣州市\",360800:\"吉安市\",360900:\"宜春市\",361e3:\"抚州市\",361100:\"上饶市\"}},{id:37e4,name:\"山东省\",alias:\"山东\",type:0,cities:{370100:\"济南市\",370200:\"青岛市\",370300:\"淄博市\",370400:\"枣庄市\",370500:\"东营市\",370600:\"烟台市\",370700:\"潍坊市\",370800:\"济宁市\",370900:\"泰安市\",371e3:\"威海市\",371100:\"日照市\",371200:\"莱芜市\",371300:\"临沂市\",371400:\"德州市\",371500:\"聊城市\",371600:\"滨州市\",371700:\"菏泽市\"}},{id:41e4,name:\"河南省\",alias:\"河南\",type:0,cities:{410100:\"郑州市\",410200:\"开封市\",410300:\"洛阳市\",410400:\"平顶山市\",410500:\"安阳市\",410600:\"鹤壁市\",410700:\"新乡市\",410800:\"焦作市\",410900:\"濮阳市\",411e3:\"许昌市\",411100:\"漯河市\",411200:\"三门峡市\",411300:\"南阳市\",411400:\"商丘市\",411500:\"信阳市\",411600:\"周口市\",411700:\"驻马店市\",419001:\"济源市\"}},{id:42e4,name:\"湖北省\",alias:\"湖北\",type:0,cities:{420100:\"武汉市\",420200:\"黄石市\",420300:\"十堰市\",420500:\"宜昌市\",420600:\"襄樊市\",420700:\"鄂州市\",420800:\"荆门市\",420900:\"孝感市\",421e3:\"荆州市\",421100:\"黄冈市\",421200:\"咸宁市\",421300:\"随州市\",422800:\"恩施土家族苗族自治州\",429004:\"仙桃市\",429005:\"潜江市\",429006:\"天门市\",429021:\"神农架林区\"}},{id:43e4,name:\"湖南省\",alias:\"湖南\",type:0,cities:{430100:\"长沙市\",430200:\"株洲市\",430300:\"湘潭市\",430400:\"衡阳市\",430500:\"邵阳市\",430600:\"岳阳市\",430700:\"常德市\",430800:\"张家界市\",430900:\"益阳市\",431e3:\"郴州市\",431100:\"永州市\",431200:\"怀化市\",431300:\"娄底市\",433100:\"湘西土家族苗族自治州\"}},{id:44e4,name:\"广东省\",alias:\"广东\",type:0,cities:{440100:\"广州市\",440200:\"韶关市\",440300:\"深圳市\",440400:\"珠海市\",440500:\"汕头市\",440600:\"佛山市\",440700:\"江门市\",440800:\"湛江市\",440900:\"茂名市\",441200:\"肇庆市\",441300:\"惠州市\",441400:\"梅州市\",441500:\"汕尾市\",441600:\"河源市\",441700:\"阳江市\",441800:\"清远市\",441900:\"东莞市\",442e3:\"中山市\",445100:\"潮州市\",445200:\"揭阳市\",445300:\"云浮市\"}},{id:45e4,name:\"广西\",alias:null,type:0,cities:{450100:\"南宁市\",450200:\"柳州市\",450300:\"桂林市\",450400:\"梧州市\",450500:\"北海市\",450600:\"防城港市\",450700:\"钦州市\",450800:\"贵港市\",450900:\"玉林市\",451e3:\"百色市\",451100:\"贺州市\",451200:\"河池市\",451300:\"来宾市\",451400:\"崇左市\"}},{id:46e4,name:\"海南省\",alias:\"海南\",type:0,cities:{460100:\"海口市\",460200:\"三亚市\",469001:\"五指山市\",469002:\"琼海市\",469003:\"儋州市\",469005:\"文昌市\",469006:\"万宁市\",469007:\"东方市\",469021:\"定安县\",469022:\"屯昌县\",469023:\"澄迈县\",469024:\"临高县\",469025:\"白沙黎族自治县\",469026:\"昌江黎族自治县\",469027:\"乐东黎族自治县\",469028:\"陵水黎族自治县\",469029:\"保亭黎族苗族自治县\",469030:\"琼中黎族苗族自治县\"}},{id:51e4,name:\"四川省\",alias:\"四川\",type:0,cities:{510100:\"成都市\",510300:\"自贡市\",510400:\"攀枝花市\",510500:\"泸州市\",510600:\"德阳市\",510700:\"绵阳市\",510800:\"广元市\",510900:\"遂宁市\",511e3:\"内江市\",511100:\"乐山市\",511300:\"南充市\",511400:\"眉山市\",511500:\"宜宾市\",511600:\"广安市\",511700:\"达州市\",511800:\"雅安市\",511900:\"巴中市\",512e3:\"资阳市\",513200:\"阿坝藏族羌族自治州\",513300:\"甘孜藏族自治州\",513400:\"凉山彝族自治州\"}},{id:52e4,name:\"贵州省\",alias:\"贵州\",type:0,cities:{520100:\"贵阳市\",520200:\"六盘水市\",520300:\"遵义市\",520400:\"安顺市\",522200:\"铜仁地区\",522300:\"黔西南布依族苗族自治州\",522400:\"毕节地区\",522600:\"黔东南苗族侗族自治州\",522700:\"黔南布依族苗族自治州\"}},{id:53e4,name:\"云南省\",alias:\"云南\",type:0,cities:{530100:\"昆明市\",530300:\"曲靖市\",530400:\"玉溪市\",530500:\"保山市\",530600:\"昭通市\",530700:\"丽江市\",530800:\"普洱市\",530900:\"临沧市\",532300:\"楚雄彝族自治州\",532500:\"红河哈尼族彝族自治州\",532600:\"文山壮族苗族自治州\",532800:\"西双版纳傣族自治州\",532900:\"大理白族自治州\",533100:\"德宏傣族景颇族自治州\",533300:\"怒江傈僳族自治州\",533400:\"迪庆藏族自治州\"}},{id:54e4,name:\"西藏\",alias:null,type:0,cities:{540100:\"拉萨市\",542100:\"昌都地区\",542200:\"山南地区\",542300:\"日喀则地区\",542400:\"那曲地区\",542500:\"阿里地区\",542600:\"林芝地区\"}},{id:61e4,name:\"陕西省\",alias:\"陕西\",type:0,cities:{610100:\"西安市\",610200:\"铜川市\",610300:\"宝鸡市\",610400:\"咸阳市\",610500:\"渭南市\",610600:\"延安市\",610700:\"汉中市\",610800:\"榆林市\",610900:\"安康市\",611e3:\"商洛市\"}},{id:62e4,name:\"甘肃省\",alias:\"甘肃\",type:0,cities:{620100:\"兰州市\",620200:\"嘉峪关市\",620300:\"金昌市\",620400:\"白银市\",620500:\"天水市\",620600:\"武威市\",620700:\"张掖市\",620800:\"平凉市\",620900:\"酒泉市\",621e3:\"庆阳市\",621100:\"定西市\",621200:\"陇南市\",622900:\"临夏回族自治州\",623e3:\"甘南藏族自治州\"}},{id:63e4,name:\"青海省\",alias:\"青海\",type:0,cities:{630100:\"西宁市\",632100:\"海东地区\",632200:\"海北藏族自治州\",632300:\"黄南藏族自治州\",632500:\"海南藏族自治州\",632600:\"果洛藏族自治州\",632700:\"玉树藏族自治州\",632800:\"海西蒙古族藏族自治州\"}},{id:64e4,name:\"宁夏\",alias:null,type:0,cities:{640100:\"银川市\",640200:\"石嘴山市\",640300:\"吴忠市\",640400:\"固原市\",640500:\"中卫市\"}},{id:65e4,name:\"新疆\",alias:null,type:0,cities:{650100:\"乌鲁木齐市\",650200:\"克拉玛依市\",652100:\"吐鲁番地区\",652200:\"哈密地区\",652300:\"昌吉回族自治州\",652700:\"博尔塔拉蒙古自治州\",652800:\"巴音郭楞蒙古自治州\",652900:\"阿克苏地区\",653e3:\"克孜勒苏柯尔克孜自治州\",653100:\"喀什地区\",653200:\"和田地区\",654e3:\"伊犁哈萨克自治州\",654200:\"塔城地区\",654300:\"阿勒泰地区\",659001:\"石河子市\",659002:\"阿拉尔市\",659003:\"图木舒克市\",659004:\"五家渠市\"}},{id:71e4,name:\"台湾省\",alias:\"台湾\",type:0,cities:{710100:\"台北市\",710200:\"高雄市\",710300:\"基隆市\",710400:\"台中市\",710500:\"台南市\",710600:\"新竹市\",710700:\"嘉义市\",710900:\"台北县\",711e3:\"宜兰县\",711100:\"桃园县\",711200:\"新竹县\",711300:\"苗栗县\",711400:\"台中县\",711500:\"彰化县\",711600:\"南投县\",711700:\"云林县\",711800:\"嘉义县\",711900:\"台南县\",712e3:\"高雄县\",712100:\"屏东县\",712200:\"澎湖县\",712300:\"台东县\",712400:\"花莲县\"}},{id:1e6,name:\"海外\",alias:\"海外\",type:3,cities:{1000100:\"阿尔及利亚\",1000200:\"阿根廷\",1000300:\"阿联酋\",1000400:\"埃及\",1000500:\"爱尔兰\",1000600:\"奥地利\",1000700:\"澳大利亚\",1000800:\"巴哈马\",1000900:\"巴基斯坦\",1001e3:\"巴西\",1001100:\"白俄罗斯\",1001200:\"比利时\",1001300:\"冰岛\",1001400:\"波兰\",1001500:\"玻利维亚\",1001600:\"伯利兹\",1001700:\"朝鲜\",1001800:\"丹麦\",1001900:\"德国\",1002e3:\"俄罗斯\",1002100:\"厄瓜多尔\",1002200:\"法国\",1002300:\"菲律宾\",1002400:\"芬兰\",1002500:\"哥伦比亚\",1002600:\"古巴\",1002700:\"关岛\",1002800:\"哈萨克斯坦\",1002900:\"韩国\",1003e3:\"荷兰\",1003100:\"加拿大\",1003200:\"加纳\",1003300:\"柬埔寨\",1003400:\"捷克\",1003500:\"卡塔尔\",1003600:\"科威特\",1003700:\"克罗地亚\",1003800:\"肯尼亚\",1003900:\"老挝\",1004e3:\"卢森堡\",1004100:\"罗马尼亚\",1004200:\"马尔代夫\",1004300:\"马来西亚\",1004400:\"美国\",1004500:\"蒙古\",1004600:\"孟加拉\",1004700:\"秘鲁\",1004800:\"缅甸\",1004900:\"摩洛哥\",1005e3:\"墨西哥\",1005100:\"南非\",1005200:\"尼日利亚\",1005300:\"挪威\",1005400:\"葡萄牙\",1005500:\"日本\",1005600:\"瑞典\",1005700:\"瑞士\",1005800:\"沙特阿拉伯\",1005900:\"斯里兰卡\",1006e3:\"苏丹\",1006100:\"泰国\",1006200:\"坦桑尼亚\",1006300:\"土耳其\",1006400:\"委内瑞拉\",1006500:\"乌克兰\",1006600:\"西班牙\",1006700:\"希腊\",1006800:\"新加坡\",1006900:\"新西兰\",1007e3:\"匈牙利\",1007100:\"伊拉克\",1007200:\"伊朗\",1007300:\"以色列\",1007400:\"意大利\",1007500:\"印度\",1007600:\"印度尼西亚\",1007700:\"英国\",1007800:\"越南\",1007900:\"智利\",101e4:\"其它\"}}]";
		JSONArray citys;
		try {
			citys = new JSONArray(ss);
			for(int i=0;i<citys.length();i++){
				JSONObject city =  citys.getJSONObject(i);
				 direct.put(city.getString("name"), city.getInt("id"));
				 JSONObject area = city.getJSONObject("cities");
				 Iterator it  =area.keys();
				  while (it.hasNext()) {
					 // Integer id = (int)Float.parseFloat(((String)it.next()));
					  String id = (String) it.next();
					  String text  = area.getString(id);
					  Integer _id = (int)Float.parseFloat((id));
					  //System.out.println();
					//  Integer id =  (Integer) it.next();
					  direct.put(text,_id );
					  
				  }
				//System.out.println(citys.getJSONObject(i).getString("id"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	public static void main(String[] args) throws JSONException {
		System.out.println(direct.get("上海"));
		/*
		 * [{
        id: 1,
        name: "直辖市",
        cities: {
            11e4: "北京市",
            12e4: "天津市",
            31e4: "上海市",
            5e5: "重庆市"
        }
    },
    {
        id: 2,
        name: "特别行政区",
        cities: {
            81e4: "香港",
            82e4: "澳门"
        }
    },
    {
        id: 13e4,
        name: "河北省",
        alias: "河北",
        type: 0,
        cities: {
            130100 : "石家庄市",
            130200 : "唐山市",
            130300 : "秦皇岛市",
            130400 : "邯郸市",
            130500 : "邢台市",
            130600 : "保定市",
            130700 : "张家口市",
            130800 : "承德市",
            130900 : "沧州市",
            131e3: "廊坊市",
            131100 : "衡水市"
        }
    },
    {
        id: 14e4,
        name: "山西省",
        alias: "山西",
        type: 0,
        cities: {
            140100 : "太原市",
            140200 : "大同市",
            140300 : "阳泉市",
            140400 : "长治市",
            140500 : "晋城市",
            140600 : "朔州市",
            140700 : "晋中市",
            140800 : "运城市",
            140900 : "忻州市",
            141e3: "临汾市",
            141100 : "吕梁市"
        }
    },
    {
        id: 15e4,
        name: "内蒙古",
        alias: null,
        type: 0,
        cities: {
            150100 : "呼和浩特市",
            150200 : "包头市",
            150300 : "乌海市",
            150400 : "赤峰市",
            150500 : "通辽市",
            150600 : "鄂尔多斯市",
            150700 : "呼伦贝尔市",
            150800 : "巴彦淖尔市",
            150900 : "乌兰察布市",
            152200 : "兴安盟",
            152500 : "锡林郭勒盟",
            152900 : "阿拉善盟"
        }
    },
    {
        id: 21e4,
        name: "辽宁省",
        alias: "辽宁",
        type: 0,
        cities: {
            210100 : "沈阳市",
            210200 : "大连市",
            210300 : "鞍山市",
            210400 : "抚顺市",
            210500 : "本溪市",
            210600 : "丹东市",
            210700 : "锦州市",
            210800 : "营口市",
            210900 : "阜新市",
            211e3: "辽阳市",
            211100 : "盘锦市",
            211200 : "铁岭市",
            211300 : "朝阳市",
            211400 : "葫芦岛市"
        }
    },
    {
        id: 22e4,
        name: "吉林省",
        alias: "吉林",
        type: 0,
        cities: {
            220100 : "长春市",
            220200 : "吉林市",
            220300 : "四平市",
            220400 : "辽源市",
            220500 : "通化市",
            220600 : "白山市",
            220700 : "松原市",
            220800 : "白城市",
            222400 : "延边朝鲜族自治州"
        }
    },
    {
        id: 23e4,
        name: "黑龙江省",
        alias: "黑龙江",
        type: 0,
        cities: {
            230100 : "哈尔滨市",
            230200 : "齐齐哈尔市",
            230300 : "鸡西市",
            230400 : "鹤岗市",
            230500 : "双鸭山市",
            230600 : "大庆市",
            230700 : "伊春市",
            230800 : "佳木斯市",
            230900 : "七台河市",
            231e3: "牡丹江市",
            231100 : "黑河市",
            231200 : "绥化市",
            232700 : "大兴安岭地区"
        }
    },
    {
        id: 32e4,
        name: "江苏省",
        alias: "江苏",
        type: 0,
        cities: {
            320100 : "南京市",
            320200 : "无锡市",
            320300 : "徐州市",
            320400 : "常州市",
            320500 : "苏州市",
            320600 : "南通市",
            320700 : "连云港市",
            320800 : "淮安市",
            320900 : "盐城市",
            321e3: "扬州市",
            321100 : "镇江市",
            321200 : "泰州市",
            321300 : "宿迁市"
        }
    },
    {
        id: 33e4,
        name: "浙江省",
        alias: "浙江",
        type: 0,
        cities: {
            330100 : "杭州市",
            330200 : "宁波市",
            330300 : "温州市",
            330400 : "嘉兴市",
            330500 : "湖州市",
            330600 : "绍兴市",
            330700 : "金华市",
            330800 : "衢州市",
            330900 : "舟山市",
            331e3: "台州市",
            331100 : "丽水市"
        }
    },
    {
        id: 34e4,
        name: "安徽省",
        alias: "安徽",
        type: 0,
        cities: {
            340100 : "合肥市",
            340200 : "芜湖市",
            340300 : "蚌埠市",
            340400 : "淮南市",
            340500 : "马鞍山市",
            340600 : "淮北市",
            340700 : "铜陵市",
            340800 : "安庆市",
            341e3: "黄山市",
            341100 : "滁州市",
            341200 : "阜阳市",
            341300 : "宿州市",
            341400 : "巢湖市",
            341500 : "六安市",
            341600 : "亳州市",
            341700 : "池州市",
            341800 : "宣城市"
        }
    },
    {
        id: 35e4,
        name: "福建省",
        alias: "福建",
        type: 0,
        cities: {
            350100 : "福州市",
            350200 : "厦门市",
            350300 : "莆田市",
            350400 : "三明市",
            350500 : "泉州市",
            350600 : "漳州市",
            350700 : "南平市",
            350800 : "龙岩市",
            350900 : "宁德市"
        }
    },
    {
        id: 36e4,
        name: "江西省",
        alias: "江西",
        type: 0,
        cities: {
            360100 : "南昌市",
            360200 : "景德镇市",
            360300 : "萍乡市",
            360400 : "九江市",
            360500 : "新余市",
            360600 : "鹰潭市",
            360700 : "赣州市",
            360800 : "吉安市",
            360900 : "宜春市",
            361e3: "抚州市",
            361100 : "上饶市"
        }
    },
    {
        id: 37e4,
        name: "山东省",
        alias: "山东",
        type: 0,
        cities: {
            370100 : "济南市",
            370200 : "青岛市",
            370300 : "淄博市",
            370400 : "枣庄市",
            370500 : "东营市",
            370600 : "烟台市",
            370700 : "潍坊市",
            370800 : "济宁市",
            370900 : "泰安市",
            371e3: "威海市",
            371100 : "日照市",
            371200 : "莱芜市",
            371300 : "临沂市",
            371400 : "德州市",
            371500 : "聊城市",
            371600 : "滨州市",
            371700 : "菏泽市"
        }
    },
    {
        id: 41e4,
        name: "河南省",
        alias: "河南",
        type: 0,
        cities: {
            410100 : "郑州市",
            410200 : "开封市",
            410300 : "洛阳市",
            410400 : "平顶山市",
            410500 : "安阳市",
            410600 : "鹤壁市",
            410700 : "新乡市",
            410800 : "焦作市",
            410900 : "濮阳市",
            411e3: "许昌市",
            411100 : "漯河市",
            411200 : "三门峡市",
            411300 : "南阳市",
            411400 : "商丘市",
            411500 : "信阳市",
            411600 : "周口市",
            411700 : "驻马店市",
            419001 : "济源市"
        }
    },
    {
        id: 42e4,
        name: "湖北省",
        alias: "湖北",
        type: 0,
        cities: {
            420100 : "武汉市",
            420200 : "黄石市",
            420300 : "十堰市",
            420500 : "宜昌市",
            420600 : "襄樊市",
            420700 : "鄂州市",
            420800 : "荆门市",
            420900 : "孝感市",
            421e3: "荆州市",
            421100 : "黄冈市",
            421200 : "咸宁市",
            421300 : "随州市",
            422800 : "恩施土家族苗族自治州",
            429004 : "仙桃市",
            429005 : "潜江市",
            429006 : "天门市",
            429021 : "神农架林区"
        }
    },
    {
        id: 43e4,
        name: "湖南省",
        alias: "湖南",
        type: 0,
        cities: {
            430100 : "长沙市",
            430200 : "株洲市",
            430300 : "湘潭市",
            430400 : "衡阳市",
            430500 : "邵阳市",
            430600 : "岳阳市",
            430700 : "常德市",
            430800 : "张家界市",
            430900 : "益阳市",
            431e3: "郴州市",
            431100 : "永州市",
            431200 : "怀化市",
            431300 : "娄底市",
            433100 : "湘西土家族苗族自治州"
        }
    },
    {
        id: 44e4,
        name: "广东省",
        alias: "广东",
        type: 0,
        cities: {
            440100 : "广州市",
            440200 : "韶关市",
            440300 : "深圳市",
            440400 : "珠海市",
            440500 : "汕头市",
            440600 : "佛山市",
            440700 : "江门市",
            440800 : "湛江市",
            440900 : "茂名市",
            441200 : "肇庆市",
            441300 : "惠州市",
            441400 : "梅州市",
            441500 : "汕尾市",
            441600 : "河源市",
            441700 : "阳江市",
            441800 : "清远市",
            441900 : "东莞市",
            442e3: "中山市",
            445100 : "潮州市",
            445200 : "揭阳市",
            445300 : "云浮市"
        }
    },
    {
        id: 45e4,
        name: "广西",
        alias: null,
        type: 0,
        cities: {
            450100 : "南宁市",
            450200 : "柳州市",
            450300 : "桂林市",
            450400 : "梧州市",
            450500 : "北海市",
            450600 : "防城港市",
            450700 : "钦州市",
            450800 : "贵港市",
            450900 : "玉林市",
            451e3: "百色市",
            451100 : "贺州市",
            451200 : "河池市",
            451300 : "来宾市",
            451400 : "崇左市"
        }
    },
    {
        id: 46e4,
        name: "海南省",
        alias: "海南",
        type: 0,
        cities: {
            460100 : "海口市",
            460200 : "三亚市",
            469001 : "五指山市",
            469002 : "琼海市",
            469003 : "儋州市",
            469005 : "文昌市",
            469006 : "万宁市",
            469007 : "东方市",
            469021 : "定安县",
            469022 : "屯昌县",
            469023 : "澄迈县",
            469024 : "临高县",
            469025 : "白沙黎族自治县",
            469026 : "昌江黎族自治县",
            469027 : "乐东黎族自治县",
            469028 : "陵水黎族自治县",
            469029 : "保亭黎族苗族自治县",
            469030 : "琼中黎族苗族自治县"
        }
    },
    {
        id: 51e4,
        name: "四川省",
        alias: "四川",
        type: 0,
        cities: {
            510100 : "成都市",
            510300 : "自贡市",
            510400 : "攀枝花市",
            510500 : "泸州市",
            510600 : "德阳市",
            510700 : "绵阳市",
            510800 : "广元市",
            510900 : "遂宁市",
            511e3: "内江市",
            511100 : "乐山市",
            511300 : "南充市",
            511400 : "眉山市",
            511500 : "宜宾市",
            511600 : "广安市",
            511700 : "达州市",
            511800 : "雅安市",
            511900 : "巴中市",
            512e3: "资阳市",
            513200 : "阿坝藏族羌族自治州",
            513300 : "甘孜藏族自治州",
            513400 : "凉山彝族自治州"
        }
    },
    {
        id: 52e4,
        name: "贵州省",
        alias: "贵州",
        type: 0,
        cities: {
            520100 : "贵阳市",
            520200 : "六盘水市",
            520300 : "遵义市",
            520400 : "安顺市",
            522200 : "铜仁地区",
            522300 : "黔西南布依族苗族自治州",
            522400 : "毕节地区",
            522600 : "黔东南苗族侗族自治州",
            522700 : "黔南布依族苗族自治州"
        }
    },
    {
        id: 53e4,
        name: "云南省",
        alias: "云南",
        type: 0,
        cities: {
            530100 : "昆明市",
            530300 : "曲靖市",
            530400 : "玉溪市",
            530500 : "保山市",
            530600 : "昭通市",
            530700 : "丽江市",
            530800 : "普洱市",
            530900 : "临沧市",
            532300 : "楚雄彝族自治州",
            532500 : "红河哈尼族彝族自治州",
            532600 : "文山壮族苗族自治州",
            532800 : "西双版纳傣族自治州",
            532900 : "大理白族自治州",
            533100 : "德宏傣族景颇族自治州",
            533300 : "怒江傈僳族自治州",
            533400 : "迪庆藏族自治州"
        }
    },
    {
        id: 54e4,
        name: "西藏",
        alias: null,
        type: 0,
        cities: {
            540100 : "拉萨市",
            542100 : "昌都地区",
            542200 : "山南地区",
            542300 : "日喀则地区",
            542400 : "那曲地区",
            542500 : "阿里地区",
            542600 : "林芝地区"
        }
    },
    {
        id: 61e4,
        name: "陕西省",
        alias: "陕西",
        type: 0,
        cities: {
            610100 : "西安市",
            610200 : "铜川市",
            610300 : "宝鸡市",
            610400 : "咸阳市",
            610500 : "渭南市",
            610600 : "延安市",
            610700 : "汉中市",
            610800 : "榆林市",
            610900 : "安康市",
            611e3: "商洛市"
        }
    },
    {
        id: 62e4,
        name: "甘肃省",
        alias: "甘肃",
        type: 0,
        cities: {
            620100 : "兰州市",
            620200 : "嘉峪关市",
            620300 : "金昌市",
            620400 : "白银市",
            620500 : "天水市",
            620600 : "武威市",
            620700 : "张掖市",
            620800 : "平凉市",
            620900 : "酒泉市",
            621e3: "庆阳市",
            621100 : "定西市",
            621200 : "陇南市",
            622900 : "临夏回族自治州",
            623e3: "甘南藏族自治州"
        }
    },
    {
        id: 63e4,
        name: "青海省",
        alias: "青海",
        type: 0,
        cities: {
            630100 : "西宁市",
            632100 : "海东地区",
            632200 : "海北藏族自治州",
            632300 : "黄南藏族自治州",
            632500 : "海南藏族自治州",
            632600 : "果洛藏族自治州",
            632700 : "玉树藏族自治州",
            632800 : "海西蒙古族藏族自治州"
        }
    },
    {
        id: 64e4,
        name: "宁夏",
        alias: null,
        type: 0,
        cities: {
            640100 : "银川市",
            640200 : "石嘴山市",
            640300 : "吴忠市",
            640400 : "固原市",
            640500 : "中卫市"
        }
    },
    {
        id: 65e4,
        name: "新疆",
        alias: null,
        type: 0,
        cities: {
            650100 : "乌鲁木齐市",
            650200 : "克拉玛依市",
            652100 : "吐鲁番地区",
            652200 : "哈密地区",
            652300 : "昌吉回族自治州",
            652700 : "博尔塔拉蒙古自治州",
            652800 : "巴音郭楞蒙古自治州",
            652900 : "阿克苏地区",
            653e3: "克孜勒苏柯尔克孜自治州",
            653100 : "喀什地区",
            653200 : "和田地区",
            654e3: "伊犁哈萨克自治州",
            654200 : "塔城地区",
            654300 : "阿勒泰地区",
            659001 : "石河子市",
            659002 : "阿拉尔市",
            659003 : "图木舒克市",
            659004 : "五家渠市"
        }
    },
    {
        id: 71e4,
        name: "台湾省",
        alias: "台湾",
        type: 0,
        cities: {
            710100 : "台北市",
            710200 : "高雄市",
            710300 : "基隆市",
            710400 : "台中市",
            710500 : "台南市",
            710600 : "新竹市",
            710700 : "嘉义市",
            710900 : "台北县",
            711e3: "宜兰县",
            711100 : "桃园县",
            711200 : "新竹县",
            711300 : "苗栗县",
            711400 : "台中县",
            711500 : "彰化县",
            711600 : "南投县",
            711700 : "云林县",
            711800 : "嘉义县",
            711900 : "台南县",
            712e3: "高雄县",
            712100 : "屏东县",
            712200 : "澎湖县",
            712300 : "台东县",
            712400 : "花莲县"
        }
    },
    {
        id: 1e6,
        name: "海外",
        alias: "海外",
        type: 3,
        cities: {
            1000100 : "阿尔及利亚",
            1000200 : "阿根廷",
            1000300 : "阿联酋",
            1000400 : "埃及",
            1000500 : "爱尔兰",
            1000600 : "奥地利",
            1000700 : "澳大利亚",
            1000800 : "巴哈马",
            1000900 : "巴基斯坦",
            1001e3: "巴西",
            1001100 : "白俄罗斯",
            1001200 : "比利时",
            1001300 : "冰岛",
            1001400 : "波兰",
            1001500 : "玻利维亚",
            1001600 : "伯利兹",
            1001700 : "朝鲜",
            1001800 : "丹麦",
            1001900 : "德国",
            1002e3: "俄罗斯",
            1002100 : "厄瓜多尔",
            1002200 : "法国",
            1002300 : "菲律宾",
            1002400 : "芬兰",
            1002500 : "哥伦比亚",
            1002600 : "古巴",
            1002700 : "关岛",
            1002800 : "哈萨克斯坦",
            1002900 : "韩国",
            1003e3: "荷兰",
            1003100 : "加拿大",
            1003200 : "加纳",
            1003300 : "柬埔寨",
            1003400 : "捷克",
            1003500 : "卡塔尔",
            1003600 : "科威特",
            1003700 : "克罗地亚",
            1003800 : "肯尼亚",
            1003900 : "老挝",
            1004e3: "卢森堡",
            1004100 : "罗马尼亚",
            1004200 : "马尔代夫",
            1004300 : "马来西亚",
            1004400 : "美国",
            1004500 : "蒙古",
            1004600 : "孟加拉",
            1004700 : "秘鲁",
            1004800 : "缅甸",
            1004900 : "摩洛哥",
            1005e3: "墨西哥",
            1005100 : "南非",
            1005200 : "尼日利亚",
            1005300 : "挪威",
            1005400 : "葡萄牙",
            1005500 : "日本",
            1005600 : "瑞典",
            1005700 : "瑞士",
            1005800 : "沙特阿拉伯",
            1005900 : "斯里兰卡",
            1006e3: "苏丹",
            1006100 : "泰国",
            1006200 : "坦桑尼亚",
            1006300 : "土耳其",
            1006400 : "委内瑞拉",
            1006500 : "乌克兰",
            1006600 : "西班牙",
            1006700 : "希腊",
            1006800 : "新加坡",
            1006900 : "新西兰",
            1007e3: "匈牙利",
            1007100 : "伊拉克",
            1007200 : "伊朗",
            1007300 : "以色列",
            1007400 : "意大利",
            1007500 : "印度",
            1007600 : "印度尼西亚",
            1007700 : "英国",
            1007800 : "越南",
            1007900 : "智利",
            101e4: "其他"
        }
    }]
		 * 
		 * */
	
		//System.out.println(direct.get("阿尔及利亚"));
		
	}
}
