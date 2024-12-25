package cn.lcx.design.pattern.lesson14;

/**
 * @author： lichenxu
 * @date： 2024/11/1815:50
 * @description： \
 * @version： v1.0
 */
public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {

    private CredentialStorage credentialStorage;

    public DefaultApiAuthenticatorImpl() { this.credentialStorage = new MysqlCredentialStorage(); }

    public DefaultApiAuthenticatorImpl(CredentialStorage credentialStorage) { this.credentialStorage = credentialStorage; }

    @Override
    public void auth(String url) {

    }

    @Override
    public void auth(ApiRequest apiRequest) {

    }
}
