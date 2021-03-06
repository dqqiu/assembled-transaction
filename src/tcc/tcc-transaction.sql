CREATE TABLE `TCC_TRANSACTION` (
`XID`  varchar(50) NOT NULL COMMENT '事务ID' ,
`REGION` varchar(50) NOT NULL COMMENT '事务域' ,
`GLOBAL_XID`  varbinary(32) NOT NULL COMMENT '全局事务ID' ,
`BRANCH_QUALIFIER`  varbinary(32) NOT NULL COMMENT '分支资格' ,
`TRANSACTION_STATUS`  smallint(6) NOT NULL COMMENT '事务状态' ,
`TRANSACTION_TYPE`  smallint(6) NOT NULL COMMENT '事务类型' ,
`RETRY_TIME`  smallint(6) NULL DEFAULT 0 COMMENT '重试次数' ,
`CREATE_TIME`  datetime NULL COMMENT '创建时间' ,
`UPDATE_TIME`  datetime NULL COMMENT '修改时间' ,
`CREATE_BY`  varchar(50) NULL COMMENT '创建人' ,
`UPDATE_BY`  varchar(50) NULL COMMENT '修改人' ,
`VERSION`  bigint(15) NULL DEFAULT 0 COMMENT '版本号' ,
PRIMARY KEY (`XID`),
UNIQUE INDEX `UNIQUE_GX_BQ` (`GLOBAL_XID`, `BRANCH_QUALIFIER`) USING BTREE 
)
;