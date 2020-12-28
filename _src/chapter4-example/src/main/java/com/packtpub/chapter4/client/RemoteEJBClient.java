package com.packtpub.chapter4.client;

import java.security.Security;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;
import org.jboss.sasl.JBossSaslProvider;

import com.packtpub.chapter4.ejb.SingletonBean;
import com.packtpub.chapter4.ejb.SingletonBeanRemote;
import com.packtpub.chapter4.entity.Property;

public class RemoteEJBClient {
	
	static {
		Security.addProvider(new JBossSaslProvider());
	}

	public static void main(String[] args) throws Exception {
		testRemoteEJB();

	}

	private static void testRemoteEJB() throws NamingException {

		final SingletonBeanRemote ejb = lookupEJB();
		ejb.save("entry", "value");
		List<Property> list = ejb.getProperties();
		System.out.println(list);
	}

	private static SingletonBeanRemote lookupEJB() throws NamingException {

		Properties clientProperties = new Properties();
        clientProperties.put("endpoint.name", "client-endpoint");
        clientProperties.put("remote.connections", "default");
        clientProperties.put("remote.connection.default.port", "8080");
        clientProperties.put("remote.connection.default.host", "localhost");
        clientProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        clientProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
 
        EJBClientConfiguration ejbClientConfiguration = new PropertiesBasedEJBClientConfiguration(clientProperties);
        ContextSelector<EJBClientContext> ejbContextSelector = new ConfigBasedEJBClientContextSelector(
                ejbClientConfiguration);
 
        EJBClientContext.setSelector(ejbContextSelector);
		
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		final String appName = "";
		final String moduleName = "chapter4-webapp-example-0.0.1-SNAPSHOT";
		final String distinctName = "";

		final String beanName = SingletonBean.class.getSimpleName();

		final String viewClassName = SingletonBeanRemote.class.getName();

		return (SingletonBeanRemote) context.lookup("ejb:" + appName + "/"
				+ moduleName + "/" + distinctName + "/" + beanName + "!"
				+ viewClassName);
	}
}
