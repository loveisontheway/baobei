package com.yaozhitech.baobei.child.enums;

public enum WaterTypeEnum {

	POOL("1DAF7609-CA19-41FE-B9FE-1727101BEECF","池塘")
	,RIVER("F856BC48-5FBF-4DD0-AA15-16F8FA78C635","内河")
	,DITCH("FB26D346-DCF0-4966-BEA7-0132D7B27E5A","沟渠")
	,STREAM("384DB048-FBBE-45A5-B05A-6A79C6A31266","溪流")
	,OTHER("22EEFA57-52B2-4306-A1DD-BD27AA6E4E4D","其他");

    private String code;
    private String remark;

    WaterTypeEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
