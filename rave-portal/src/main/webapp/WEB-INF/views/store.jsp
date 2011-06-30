<%--
   Licensed to the Apache Software Foundation (ASF) under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ASF licenses this file
   to you under the Apache License, Version 2.0 (the
   "License"); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at
  
     http://www.apache.org/licenses/LICENSE-2.0
  
   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License.
  
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="rave"%>

<rave:rave_generic_page pageTitle="Widget Store - Rave">
<div id="banner">
    <span class="backToPage">
        <a href="<spring:url value="/index.html" />">Back to Rave</a>
    </span>
</div>

<div id="navigation">

</div>
<div id="content">
    <c:forEach var="widget" items="${widgets}">
        <div class="storeItem">
            <div class="storeItemLeft">
                <c:if test="${not empty widget.thumbnailUrl}">
                    <img class="storeWidgetThumbnail" src="${widget.thumbnailUrl}"
                         title="${widget.title}" alt="${widget.title}"
                         width="120" height="60"/>
                </c:if>
                <div class="widgetType">${widget.type}</div>
            </div>
            <div class="storeItemCenter">
                <div id="widgetAdded_${widget.id}" class="storeButton">
                    <button class="storeItemButton"
                            id="addWidget_${widget.id}"
                            onclick="rave.api.rpc.addWidgetToPage({widgetId: ${widget.id}, pageId: ${referringPageId}});">
                        Add to Page
                    </button>
                </div>
                <a class="secondaryPageItemTitle"
                   href="<spring:url value="/app/store/widget/${widget.id}" />?referringPageId=${referringPageId}">${widget.title}
                </a>

                <div class="storeWidgetAuthor">By:
                ${widget.author}</div>
                <div class="storeWidgetDesc">${widget.description}</div>
            </div>
            <div class="clear-float" >&nbsp;</div>
        </div>
    </c:forEach>
</div>
<script>
    var rave = rave || {
        getContext : function() {
            return "<spring:url value="/app/" />";
        }
    }
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script src="<spring:url value="/script/rave_api.js"/>"></script>
</rave:rave_generic_page>
