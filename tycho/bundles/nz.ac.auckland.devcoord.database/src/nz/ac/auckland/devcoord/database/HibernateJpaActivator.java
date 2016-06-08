package nz.ac.auckland.devcoord.database;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class HibernateJpaActivator extends Plugin {

    private static BundleContext context;

    @Override
    public void start(BundleContext context)throws Exception {
        HibernateJpaActivator.context = context;
    }

    public static BundleContext getContext() {
        return context;
    }
}