-- 接口信息
create table if not exists api.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestParams` text not null comment '请求参数',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陆烨霖', '17087096056', 'www.conchita-leuschke.info', 'SB', '9H9j', 6, '6141483', 21);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('袁航', '15098474612', 'www.brendan-auer.org', 'jFF', 'AxT', 6, '0', 83074845);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('杜睿渊', '15037812696', 'www.aurelia-huels.net', 'qRu', 'ZYoF', 6, '7', 13609137);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('林煜城', '17055948182', 'www.tessie-casper.com', '9WKlX', 'rK', 6, '95', 40951855);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('蒋鹏涛', '15792950522', 'www.gil-simonis.net', 'NSs', 'HBHgh', 6, '4213', 56674);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('方越彬', '14737119014', 'www.azucena-braun.info', 'KLM', 'ZTOaq', 6, '8', 3564952);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('唐潇然', '17387311261', 'www.jarred-monahan.biz', 'fsp', 'yf', 6, '5', 99);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('方正豪', '14574865061', 'www.karleen-boyer.biz', 'wrq', 'fji', 6, '504025362', 6431);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('潘语堂', '17660901475', 'www.shelly-moore.info', 'SAm', 'tJEX', 6, '4769775', 833);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙哲瀚', '15089406206', 'www.scottie-hessel.com', 'ay', 'dXYW', 6, '787', 6067437);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('任明', '15945272358', 'www.iona-corwin.net', 'BO', 'EES3', 6, '803', 57589);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('洪浩宇', '17833197826', 'www.celinda-schmidt.com', '4d', 'fK', 6, '66', 6);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('宋思远', '15625508572', 'www.alphonso-von.net', '4Thy', '2O4yk', 6, '420', 165085);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('姚金鑫', '17384424515', 'www.rey-huel.net', 'F8', 'pN', 6, '730', 61);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('傅峻熙', '14710167719', 'www.jamel-gleichner.com', 'f0vho', 'cBl2', 6, '443', 966495);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('戴潇然', '13152666501', 'www.lanette-baumbach.co', 'A3CY', 'vi', 6, '480', 34);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('崔哲瀚', '17874644663', 'www.dante-damore.name', 'kF', 'YJSL', 6, '5099369846', 9208704);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('贾绍齐', '17515518559', 'www.yessenia-davis.io', 'vpIn', 'XEaz', 6, '25', 16990617);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('高乐驹', '17379894816', 'www.agustin-reilly.org', '1BCVr', 'uc', 6, '1339308', 582477);
insert into api.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('蔡博文', '15693094246', 'www.martha-daugherty.net', 'NyKHz', 'HrE', 6, '2604369', 21200527);


-- 用户调用接口关系表
create table if not exists api.`user_interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` bigint not null comment '调用用户 id',
    `interfaceInfoId` bigint not null comment '接口 id',
    `totalNum` int default 0 not null comment '总调用次数',
    `leftNum` int default 0 not null comment '剩余调用次数',
    `status` int default 0 not null comment '0-正常，1-禁用',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '用户调用接口关系';



insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('黎志强', '0', '王思', '江琪', '陶金鑫', 0, 10, 'www.edie-nitzsche.co', '杜晓啸');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('万笑愚', '0', '郭思淼', '沈峻熙', '龚乐驹', 0, 11, 'www.rogelio-krajcik.org', '严鸿煊');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('石烨伟', '0', '秦立辉', '尹伟宸', '郝琪', 0, 12, 'www.loriann-casper.info', '周鸿涛');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('余昊强', '0', '覃思聪', '廖果', '孙文轩', 0, 13, 'www.dane-kerluke.io', '彭文轩');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('孙俊驰', '0', '石子涵', '冯天宇', '陈凯瑞', 0, 14, 'www.jaye-heathcote.info', '吴雪松');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('孙皓轩', '0', '于雪松', '邵弘文', '袁志泽', 0, 15, 'www.erwin-harvey.net', '蒋明轩');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('田立轩', '0', '傅弘文', '廖鹏煊', '孟聪健', 0, 16, 'www.ardith-cronin.co', '任风华');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('毛皓轩', '0', '秦鑫鹏', '胡思淼', '沈志泽', 0, 17, 'www.trenton-steuber.name', '贺思远');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('尹晓博', '0', '何晋鹏', '蒋黎昕', '廖立辉', 0, 18, 'www.cyndi-kassulke.com', '袁智渊');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('范思源', '0', '徐旭尧', '苏思淼', '白鹏飞', 0, 19, 'www.alfonso-stoltenberg.org', '田鹏煊');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('程绍辉', '0', '莫瑞霖', '江雪松', '洪苑博', 0, 20, 'www.annett-bartell.name', '覃立果');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('石弘文', '0', '王钰轩', '王鸿煊', '赖越彬', 0, 21, 'www.harris-goldner.co', '李立果');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('何健柏', '0', '姚子默', '廖文', '贾明哲', 0, 22, 'www.mazie-bradtke.io', '郑梓晨');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('万文博', '0', '郑子轩', '莫展鹏', '薛思源', 0, 23, 'www.julie-mcclure.io', '杜梓晨');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('陈文博', '0', '毛锦程', '方语堂', '夏立诚', 0, 24, 'www.kendall-crooks.co', '韦昊强');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('曹弘文', '0', '陈瑞霖', '卢鹏煊', '姚立果', 0, 25, 'www.tabetha-kilback.biz', '薛浩宇');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('雷俊驰', '0', '何明辉', '唐煜城', '宋立辉', 0, 26, 'www.wilfredo-nikolaus.info', '钱弘文');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('程烨华', '0', '沈博文', '阎峻熙', '段志强', 0, 27, 'www.claude-murray.net', '赖昊然');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('曾苑博', '0', '熊明哲', '田昊天', '崔鸿煊', 0, 28, 'www.hedwig-kerluke.name', '石哲瀚');
insert into api.`interface_info` (`name`, `method`, `requestParams`, `responseHeader`, `requestHeader`, `status`, `userId`, `url`, `description`) values ('罗炫明', '0', '范鹤轩', '郑彬', '武熠彤', 0, 29, 'www.shawn-sanford.net', '周哲瀚');
