package na.com.meatco.sageX3integration.entities.sageX3.soap;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "callContext")
public class CallContext {

    @JacksonXmlProperty
    private String codeLang;

    @JacksonXmlProperty
    private String poolAlias;

    @JacksonXmlProperty
    private String poolId;

    @JacksonXmlProperty
    private String requestConfig;

    public CallContext() {
    }

    public CallContext(String codeLang, String poolAlias, String poolId, String requestConfig) {
        this.codeLang = codeLang;
        this.poolAlias = poolAlias;
        this.poolId = poolId;
        this.requestConfig = requestConfig;
    }

    public String getCodeLang() {
        return codeLang;
    }

    public void setCodeLang(String codeLang) {
        this.codeLang = codeLang;
    }

    public String getPoolAlias() {
        return poolAlias;
    }

    public void setPoolAlias(String poolAlias) {
        this.poolAlias = poolAlias;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public String getRequestConfig() {
        return requestConfig;
    }

    public void setRequestConfig(String requestConfig) {
        this.requestConfig = requestConfig;
    }

    @Override
    public String toString() {
        return "CallContext{" +
                "codeLang='" + codeLang + '\'' +
                ", poolAlias='" + poolAlias + '\'' +
                ", poolId='" + poolId + '\'' +
                ", requestConfig='" + requestConfig + '\'' +
                '}';
    }
}
