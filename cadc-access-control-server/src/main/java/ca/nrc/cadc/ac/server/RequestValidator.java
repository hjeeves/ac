/*
 ************************************************************************
 *******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
 **************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
 *
 *  (c) 2014.                            (c) 2014.
 *  Government of Canada                 Gouvernement du Canada
 *  National Research Council            Conseil national de recherches
 *  Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
 *  All rights reserved                  Tous droits réservés
 *
 *  NRC disclaims any warranties,        Le CNRC dénie toute garantie
 *  expressed, implied, or               énoncée, implicite ou légale,
 *  statutory, of any kind with          de quelque nature que ce
 *  respect to the software,             soit, concernant le logiciel,
 *  including without limitation         y compris sans restriction
 *  any warranty of merchantability      toute garantie de valeur
 *  or fitness for a particular          marchande ou de pertinence
 *  purpose. NRC shall not be            pour un usage particulier.
 *  liable in any event for any          Le CNRC ne pourra en aucun cas
 *  damages, whether direct or           être tenu responsable de tout
 *  indirect, special or general,        dommage, direct ou indirect,
 *  consequential or incidental,         particulier ou général,
 *  arising from the use of the          accessoire ou fortuit, résultant
 *  software.  Neither the name          de l'utilisation du logiciel. Ni
 *  of the National Research             le nom du Conseil National de
 *  Council of Canada nor the            Recherches du Canada ni les noms
 *  names of its contributors may        de ses  participants ne peuvent
 *  be used to endorse or promote        être utilisés pour approuver ou
 *  products derived from this           promouvoir les produits dérivés
 *  software without specific prior      de ce logiciel sans autorisation
 *  written permission.                  préalable et particulière
 *                                       par écrit.
 *
 *  This file is part of the             Ce fichier fait partie du projet
 *  OpenCADC project.                    OpenCADC.
 *
 *  OpenCADC is free software:           OpenCADC est un logiciel libre ;
 *  you can redistribute it and/or       vous pouvez le redistribuer ou le
 *  modify it under the terms of         modifier suivant les termes de
 *  the GNU Affero General Public        la “GNU Affero General Public
 *  License as published by the          License” telle que publiée
 *  Free Software Foundation,            par la Free Software Foundation
 *  either version 3 of the              : soit la version 3 de cette
 *  License, or (at your option)         licence, soit (à votre gré)
 *  any later version.                   toute version ultérieure.
 *
 *  OpenCADC is distributed in the       OpenCADC est distribué
 *  hope that it will be useful,         dans l’espoir qu’il vous
 *  but WITHOUT ANY WARRANTY;            sera utile, mais SANS AUCUNE
 *  without even the implied             GARANTIE : sans même la garantie
 *  warranty of MERCHANTABILITY          implicite de COMMERCIALISABILITÉ
 *  or FITNESS FOR A PARTICULAR          ni d’ADÉQUATION À UN OBJECTIF
 *  PURPOSE.  See the GNU Affero         PARTICULIER. Consultez la Licence
 *  General Public License for           Générale Publique GNU Affero
 *  more details.                        pour plus de détails.
 *
 *  You should have received             Vous devriez avoir reçu une
 *  a copy of the GNU Affero             copie de la Licence Générale
 *  General Public License along         Publique GNU Affero avec
 *  with OpenCADC.  If not, see          OpenCADC ; si ce n’est
 *  <http://www.gnu.org/licenses/>.      pas le cas, consultez :
 *                                       <http://www.gnu.org/licenses/>.
 *
 *  $Revision: 4 $
 *
 ************************************************************************
 */
package ca.nrc.cadc.ac.server;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;

import ca.nrc.cadc.ac.Role;
import ca.nrc.cadc.auth.AuthenticationUtil;
import ca.nrc.cadc.uws.Parameter;
import ca.nrc.cadc.uws.ParameterUtil;

/**
 * Request Validator. This class extracts and validates the ID, TYPE, ROLE
 * and GURI parameters.
 *
 */
public class RequestValidator
{
    private static final Logger log = Logger.getLogger(RequestValidator.class);
    
    //private Principal principal;
    private Role role;
    private String groupID;
    
    public RequestValidator() { }

    private void clear()
    {
        //this.principal = null;
        this.role = null;
        this.groupID = null;
    }
    
    public void validate(List<Parameter> paramList)
    {
        clear();
        if (paramList == null || paramList.isEmpty())
        {
            throw new IllegalArgumentException(
                    "Missing required parameters: ID, IDTYPE, ROLE");
        }

        // ID
        //String param = ParameterUtil.findParameterValue("ID", paramList);
        //if (param == null || param.trim().isEmpty())
        //{
        //    throw new IllegalArgumentException(
        //            "ID parameter required but not found");
        //}
        //String userID = param.trim();
        //log.debug("ID: " + userID);

        // TYPE
        //param = ParameterUtil.findParameterValue("IDTYPE", paramList);
        //if (param == null || param.trim().isEmpty())
        //{
        //    throw new IllegalArgumentException(
        //            "IDTYPE parameter required but not found");
        //}
        
        //principal = 
        //    AuthenticationUtil.createPrincipal(userID, 
        //                                       param.trim());
        //log.debug("TYPE: " + param.trim());
        
        // ROLE
        String param = ParameterUtil.findParameterValue("ROLE", paramList);
        if (param == null || param.trim().isEmpty())
        {
            throw new IllegalArgumentException(
                    "ROLE parameter required but not found");
        }
        this.role = Role.toValue(param);
        log.debug("ROLE: " + role);
        
        // GROUPID
        param = ParameterUtil.findParameterValue("GROUPID", paramList);
        if (param != null)
        {
            if (param.isEmpty())
                throw new IllegalArgumentException(
                        "GROUPID parameter specified without a value");
            this.groupID = param.trim();
        }
        log.debug("GROUPID: " + groupID);
    }
    
    //public Principal getPrincipal()
    //{
    //    return principal;
    //}

    public Role getRole()
    {
        return role;
    }
    
    public String getGroupID()
    {
        return groupID;
    }

}
