package com.example.utils;

import org.junit.Test;
import org.junit.Assert;

/**
 * Markdown Parser Unit Test.
 *
 */
public class MarkdownParserUnitTest {

    private MarkdownParser mdParser = new MarkdownParser();

    @Test
    public void testTitleParse() {
        Assert.assertEquals(0, mdParser.getTitleLevel("no title here"));
        Assert.assertEquals(0, mdParser.getTitleLevel("中文非标题"));
        Assert.assertEquals(0, mdParser.getTitleLevel(" ## A title starts with sharp"));
        Assert.assertEquals(0, mdParser.getTitleLevel(""));
        Assert.assertEquals(0, mdParser.getTitleLevel(null));
        Assert.assertEquals(1, mdParser.getTitleLevel("# Hello!#"));
        Assert.assertEquals(1, mdParser.getTitleLevel("#This should work too!"));
        Assert.assertEquals(2, mdParser.getTitleLevel("## # Hello!#"));
        Assert.assertEquals(4, mdParser.getTitleLevel("####.##"));
        Assert.assertEquals(5, mdParser.getTitleLevel("##### # Hello!#"));
    }

    @Test
    public void testTextLineParse() {
        mdParser.parseTextLine("");
        mdParser.parseTextLine("null");
        mdParser.parseTextLine(null);
        mdParser.parseTextLine("![cover](ad.jpg) ![12](sp.png)");
        mdParser.parseTextLine("adad![cover](ad.jpg) ![12](sp.png)");
        mdParser.parseTextLine("adad![cover](ad.jpg) 中间一段 ![kk](ssddp.png)![alt](sp.png) 最后再" +
                "长长长长长长长长长长～![alt](sp.png)=～～长长长～～ ～～一段");
    }

    @Test
    public void testArticleDataParse() {
        // event_01 markdown
        mdParser.parseArticleData("hi string， 欢迎阅读\n\n# 激发创造，丰富生活，2020字节跳动全球员工摄影大赛投稿通道正式开启，只要参与就有机会获得惊喜大奖。\n    不能外出和远足的日子里，我们更加关注当下，留意身边正在发生的故事。全球员工摄影大赛邀请你用镜头记录所见所闻，与全球的ByteDancer共享珍贵记忆。身处不平凡的2020，期待不平凡的优秀作品。\n## 竞赛单元\n    本次比赛以“Inspire Creativity, Enrich Life”为主题，共设置特殊记“疫”、字节映像、人间百景、视频故事四个竞赛单元。\n## 赛程安排\n- 投稿截止：2020.10.31\n- 人气投票：2020.11.10-11.17\n- 奖项公布：2020.11.20\n    摄影展：2020.12\n    \n## 参赛规则\n1. 同一个人可以投稿多个竞赛单元，单张组图不限，但每个作品只能投稿一个竞赛单元；\n2. 图片作品长边不低于600像素，大小介于1M-50M，视频作品不超过5分钟；\n3. 作品必须保留Exif信息，并附上标题和文字说明；\n4. 参赛作品使用的拍摄器材不限，同时征集手机、相机作品；\n5. 允许轻量后期处理，如调色和杂物修补，不接受合成类作品；\n6. 请注意公司相关信息保密，不要泄露具体工作内容。\n## 评审机制\n    图虫编辑团队将对投稿作品进行初选，选出500组入围作品。之后由专业特邀评审最终评选出一、二、三等奖，共36组，人气奖由ByteDancer从其他464个作品中票选产生。");
        // teamBuilding_04 markdown
        mdParser.parseArticleData("hi ， 欢迎阅读\n\n![cover](teamBuilding_04.png) ## 流程：\n- 4-12 周五下午14:00 氪空间出发\n- 14:40 到达目的地\n- 14:40-18:00 蹦！玩！High！\n- 18：00 结束，吃饭。\n## 吃饭\n    五队人马分头行动\n- PM +设计 团队*潮明冰室*\n- PC 团队*云海肴*\n- BE团队*麻省理工烤鱼*\n- iOS+HR+数据团队*串烧日本料理*\n- Android+QA团队*川食公馆*\n    \n## 交通：\n- 去程：两座大巴车会带大家直达目的地；\n- 返程：自行解决（虹桥火车站 2/10号线或者打车/公交均可）\n## 地点：\n    申长路688号虹桥天地（乐图空间）\n\n 乐图空间,★★★★★,消费:¥171虹桥枢纽 体育场馆,申长路688号虹桥天地·购物中心南区L4 http://m.dianping.com/appshare/shop/95169835\n\n 很多图可以去点评上看，我实在是扣图扣不动...\n    \n## 注意事项：\n    穿宽松有弹性适合运动的裤子！");
        // teamBuilding_09
        mdParser.parseArticleData("hi ， 欢迎阅读\n\n团建商家\n    商家名称：淀山湖皮划艇CS烧烤露营基地\n    地址：青浦区西岑山深支路18号\n    停车方式：山深支路进来第二坐桥前（边上有移动通迅塔），看到桥头四叉港牌子左转，车子停河边水泥路上，（车子可以前面开出去，不用掉头）靠边前后停好，不影响其他车辆通行。\n    http://www.dianping.com/shop/l8IPTwEC9TMtzS55\n    https://v.qq.com/x/page/k3126p0uvja.html\n    团建安排\n    09:50  利丰楼下集合上车（40人）（车辆信息：沪DF6861 戴传兵13918277223）\n    11:00   到达团建地点\n    11:15    团体自我介绍、合影\n    12:00   烧烤午餐\n    13:30   休闲活动：室内棋牌、台球、K歌\n    15:00   皮划艇\n    17:00   活动结束\n    17:30   上车回公司\n    19:00   晚餐（30人）\n    其他内容\n    休闲活动包含：环湖骑行，别墅K歌，自动麻将，钓鱼，蓝球，桌球，乒乓球，羽毛球，一千平方大草坪游戏。并免费提供器材。\n    \n    下水划船安全保障措施：\n    1. 选择风景优美流水缓慢的淀山湖湿地河道。\n    2. 下水前划船培训和安全告知\n    3. 下水队员必须穿好救生衣\n    4. 下水全程配备教练陪同。\n    5. 配备救生艇尾随，安全有保障\n    6. 应急预案：领队和救生艇配有专业电台联络，确保水上水下通信畅通。\n    \n    商家仅提供茶水，不提供酒水饮料休闲小食，所以我们自备好了饮料矿泉水零食。\n    \n    如需带宠物请提前告诉庄主，并遵守不带狗上楼上沙发的规则。\n    注意事项：\n    1. 户外运动装备自备，钓鱼请自备钓鱼竿，墨镜、户外鞋等防晒霜，运动手套。\n    2. 皮划艇为水上运动項目，难免会湿身，请多带套洗衣服。\n    3. 手机、背包请勿带着上船，以免遇水受损。\n    4. 酗酒、醉酒、怀孕、身体不适者，请勿下水划船，并听从领队安排。");
        // teamBuilding_09 markdown
        mdParser.parseArticleData("hi ， 欢迎阅读\n\n## 团建商家\n- 商家名称：淀山湖皮划艇CS烧烤露营基地\n- 地址：青浦区西岑山深支路18号\n- 停车方式：山深支路进来第二坐桥前（边上有移动通迅塔），看到桥头四叉港牌子左转，车子停河边水泥路上，（车子可以前面开出去，不用掉头）靠边前后停好，不影响其他车辆通行。\n    http://www.dianping.com/shop/l8IPTwEC9TMtzS55\n    https://v.qq.com/x/page/k3126p0uvja.html\n## 团建安排\n- 09:50  利丰楼下集合上车（40人）（车辆信息：沪DF6861 戴传兵13918277223）\n- 11:00   到达团建地点\n- 11:15    团体自我介绍、合影\n- 12:00   烧烤午餐\n- 13:30   休闲活动：室内棋牌、台球、K歌\n- 15:00   皮划艇\n- 17:00   活动结束\n- 17:30   上车回公司\n- 19:00   晚餐（30人）\n## 其他内容\n    休闲活动包含：环湖骑行，别墅K歌，自动麻将，钓鱼，蓝球，桌球，乒乓球，羽毛球，一千平方大草坪游戏。并免费提供器材。\n    \n## 下水划船安全保障措施：\n    1. 选择风景优美流水缓慢的淀山湖湿地河道。\n    2. 下水前划船培训和安全告知\n    3. 下水队员必须穿好救生衣\n    4. 下水全程配备教练陪同。\n    5. 配备救生艇尾随，安全有保障\n    6. 应急预案：领队和救生艇配有专业电台联络，确保水上水下通信畅通。\n    \n    商家仅提供茶水，不提供酒水饮料休闲小食，所以我们自备好了饮料矿泉水零食。\n    \n    如需带宠物请提前告诉庄主，并遵守不带狗上楼上沙发的规则。\n## 注意事项：\n    1. 户外运动装备自备，钓鱼请自备钓鱼竿，墨镜、户外鞋等防晒霜，运动手套。\n    2. 皮划艇为水上运动項目，难免会湿身，请多带套洗衣服。\n    3. 手机、背包请勿带着上船，以免遇水受损。\n    4. 酗酒、醉酒、怀孕、身体不适者，请勿下水划船，并听从领队安排。");
    }
}
