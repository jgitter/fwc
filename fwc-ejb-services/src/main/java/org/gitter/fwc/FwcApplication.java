package org.gitter.fwc;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// This can also be configured in the web.xml
@ApplicationPath("/rest")
public class FwcApplication extends Application {}