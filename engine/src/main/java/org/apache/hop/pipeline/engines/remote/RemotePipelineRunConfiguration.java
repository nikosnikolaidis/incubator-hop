/*! ******************************************************************************
 *
 * Hop : The Hop Orchestration Platform
 *
 * http://www.project-hop.org
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.apache.hop.pipeline.engines.remote;

import org.apache.hop.server.HopServer;
import org.apache.hop.core.gui.plugin.GuiElementType;
import org.apache.hop.core.gui.plugin.GuiPlugin;
import org.apache.hop.core.gui.plugin.GuiWidgetElement;
import org.apache.hop.core.logging.ILogChannel;
import org.apache.hop.metadata.api.HopMetadataProperty;
import org.apache.hop.metadata.api.IHopMetadataProvider;
import org.apache.hop.metadata.api.IHopMetadataSerializer;
import org.apache.hop.pipeline.config.IPipelineEngineRunConfiguration;
import org.apache.hop.pipeline.config.PipelineRunConfiguration;
import org.apache.hop.pipeline.engines.EmptyPipelineRunConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@GuiPlugin(description = "Remote pipeline run configuration widgets")
public class RemotePipelineRunConfiguration extends EmptyPipelineRunConfiguration implements IPipelineEngineRunConfiguration {

  @GuiWidgetElement(
    order = "10",
    parentId = PipelineRunConfiguration.GUI_PLUGIN_ELEMENT_PARENT_ID,
    type = GuiElementType.COMBO,
    comboValuesMethod = "getHopServerNames",
    i18nPackage = "org.apache.hop.ui.pipeline.config",
    label = "PipelineRunConfigurationDialog.HopServer.Label"
  )
  @HopMetadataProperty( key = "hop_server" )
  protected String hopServerName;

  @GuiWidgetElement(
    order = "20",
    parentId = PipelineRunConfiguration.GUI_PLUGIN_ELEMENT_PARENT_ID,
    type = GuiElementType.COMBO,
    comboValuesMethod = "getRunConfigurationNames",
    i18nPackage = "org.apache.hop.ui.pipeline.config",
    label = "PipelineRunConfigurationDialog.RunConfiguration.Label"
  )
  @HopMetadataProperty( key = "safe_mode" )
  protected String runConfigurationName;

  @GuiWidgetElement(
    order = "30",
    parentId = PipelineRunConfiguration.GUI_PLUGIN_ELEMENT_PARENT_ID,
    type = GuiElementType.TEXT,
    i18nPackage = "org.apache.hop.ui.pipeline.config",
    label = "PipelineRunConfigurationDialog.ServerPollDelay.Label"
  )
  @HopMetadataProperty( key = "server_poll_delay" )
  protected String serverPollDelay;

  @GuiWidgetElement(
    order = "40",
    parentId = PipelineRunConfiguration.GUI_PLUGIN_ELEMENT_PARENT_ID,
    type = GuiElementType.TEXT,
    i18nPackage = "org.apache.hop.ui.pipeline.config",
    label = "PipelineRunConfigurationDialog.ServerPollInterval.Label"
  )
  @HopMetadataProperty( key = "server_poll_interval" )
  protected String serverPollInterval;


  public RemotePipelineRunConfiguration() {
    super();
  }

  public RemotePipelineRunConfiguration( RemotePipelineRunConfiguration config ) {
    super( config );
    this.hopServerName = config.hopServerName;
    this.runConfigurationName = config.runConfigurationName;
    this.serverPollDelay = config.serverPollDelay;
    this.serverPollInterval = config.serverPollInterval;
  }

  public List<String> getHopServerNames( ILogChannel log, IHopMetadataProvider metadataProvider ) {
    List<String> names = new ArrayList<>();
    try {
      IHopMetadataSerializer<HopServer> serializer = metadataProvider.getSerializer( HopServer.class );
      names.addAll( serializer.listObjectNames() );
      Collections.sort( names );
    } catch ( Exception e ) {
      log.logError( "Error getting hop server names from the metadata", e );
    }
    return names;
  }

  public List<String> getRunConfigurationNames( ILogChannel log, IHopMetadataProvider metadataProvider ) {
    List<String> names = new ArrayList<>();
    try {
      IHopMetadataSerializer<PipelineRunConfiguration> serializer = metadataProvider.getSerializer( PipelineRunConfiguration.class );
      names.addAll( serializer.listObjectNames() );
      Collections.sort( names );
    } catch ( Exception e ) {
      log.logError( "Error getting the pipeline run configuration names from the metadata", e );
    }
    return names;
  }

  public RemotePipelineRunConfiguration clone() {
    return new RemotePipelineRunConfiguration( this );
  }

  /**
   * Gets hopServerName
   *
   * @return value of hopServerName
   */
  public String getHopServerName() {
    return hopServerName;
  }

  /**
   * @param hopServerName The hopServerName to set
   */
  public void setHopServerName( String hopServerName ) {
    this.hopServerName = hopServerName;
  }

  /**
   * Gets runConfigurationName
   *
   * @return value of runConfigurationName
   */
  public String getRunConfigurationName() {
    return runConfigurationName;
  }

  /**
   * @param runConfigurationName The runConfigurationName to set
   */
  public void setRunConfigurationName( String runConfigurationName ) {
    this.runConfigurationName = runConfigurationName;
  }

  /**
   * Gets serverPollDelay
   *
   * @return value of serverPollDelay
   */
  public String getServerPollDelay() {
    return serverPollDelay;
  }

  /**
   * @param serverPollDelay The serverPollDelay to set
   */
  public void setServerPollDelay( String serverPollDelay ) {
    this.serverPollDelay = serverPollDelay;
  }

  /**
   * Gets serverPollInterval
   *
   * @return value of serverPollInterval
   */
  public String getServerPollInterval() {
    return serverPollInterval;
  }

  /**
   * @param serverPollInterval The serverPollInterval to set
   */
  public void setServerPollInterval( String serverPollInterval ) {
    this.serverPollInterval = serverPollInterval;
  }
}
