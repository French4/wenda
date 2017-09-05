<#include "header.ftl">
<link rel="stylesheet" href="../styles/detail.css">
<script src="../scripts/instant.e7a17de6.js"></script>

</head>

<body class="zhi ">



    <div role="navigation" class="zu-top">
        <div class="zg-wrap modal-shifting clearfix" id="zh-top-inner">
            <a href="https://nowcoder.com/" class="zu-top-link-logo" id="zh-top-link-logo" data-za-c="view_home" data-za-a="visit_home" data-za-l="top_navigation_zhihu_logo">牛客</a>
            <div class="top-nav-profile">
                <a href="https://nowcoder.com/people/hu-yuan-24-48" class="zu-top-nav-userinfo " id=":0" role="button" aria-haspopup="true" aria-activedescendant="">
                    <span class="name">牛客</span>
                    <img class="Avatar" src="../images/res/da8e974dc_s.jpg" srcset="https://pic1.zhimg.com/da8e974dc_xs.jpg 2x" alt="牛客">
                    <span id="zh-top-nav-new-pm" class="zg-noti-number zu-top-nav-pm-count" style="visibility:hidden" data-count="0">
                    </span>
                </a>
                <ul class="top-nav-dropdown" id="top-nav-profile-dropdown" aria-labelledby=":0">
                    <li>
                    <a href="https://nowcoder.com/people/hu-yuan-24-48" tabindex="-1" id=":1">
                    <i class="zg-icon zg-icon-dd-home"></i>我的主页
                    </a>
                    </li>
                    <li>
                    <a href="https://nowcoder.com/inbox" tabindex="-1" id=":2">
                    <i class="zg-icon zg-icon-dd-pm"></i>私信
                    <span id="zh-top-nav-pm-count" class="zu-top-nav-pm-count zg-noti-number" style="visibility:hidden" data-count="0">
                    </span>
                    </a>
                    </li>
                    <li>
                    <a href="https://nowcoder.com/settings" tabindex="-1" id=":3">
                    <i class="zg-icon zg-icon-dd-settings"></i>设置
                    </a>
                    </li>
                    <li>
                    <a href="https://nowcoder.com/logout" tabindex="-1" id=":4">
                    <i class="zg-icon zg-icon-dd-logout"></i>退出
                    </a>
                    </li>
                </ul>
            </div>
            <button class="zu-top-add-question" id="zu-top-add-question">提问</button>
            <div role="search" id="zh-top-search" class="zu-top-search">
                <form method="GET" action="https://nowcoder.com/search" id="zh-top-search-form" class="zu-top-search-form">
                <input type="hidden" name="type" value="content">
                <label for="q" class="hide-text">牛客搜索</label><input type="text" class="zu-top-search-input" id="q" name="q" autocomplete="off" value="" placeholder="搜索你感兴趣的内容..." role="combobox" aria-autocomplete="list">
                <button type="submit" class="zu-top-search-button"><span class="hide-text">搜索</span><span class="sprite-global-icon-magnifier-dark"></span></button>
                </form>
            </div>
            <div id="zg-top-nav" class="zu-top-nav">
                <ul class="zu-top-nav-ul zg-clear">
                    <li class="zu-top-nav-li current" id="zh-top-nav-home">
                    <a class="zu-top-nav-link" href="https://nowcoder.com/" id="zh-top-link-home" data-za-c="view_home" data-za-a="visit_home" data-za-l="top_navigation_home">首页</a>
                    </li>
                    <li class="zu-top-nav-li " id="zh-top-nav-explore">
                    <a class="zu-top-nav-link" href="https://nowcoder.com/explore">发现</a>
                    </li>
                    <li class="top-nav-noti zu-top-nav-li ">
                    <a class="zu-top-nav-link" href="javascript:;" id="zh-top-nav-count-wrap" role="button"><span class="mobi-arrow"></span>消息<span id="zh-top-nav-count" class="zu-top-nav-count zg-noti-number" style="display: none;">0</span></a>
                    </li>
                </ul>
                <div class="zu-top-nav-live zu-noti7-popup zg-r5px no-hovercard" id="zh-top-nav-live-new" role="popup" tabindex="0">
                    <div class="zu-top-nav-live-inner zg-r5px">
                        <div class="zu-top-live-icon">&nbsp;</div>
                        <div class="zu-home-noti-inner" id="zh-top-nav-live-new-inner">
                            <div class="zm-noti7-popup-tab-container clearfix" tabindex="0" role="tablist">
                            <button class="zm-noti7-popup-tab-item message" role="tab">
                            <span class="icon">消息</span>
                            </button>
                            <button class="zm-noti7-popup-tab-item user" role="tab">
                            <span class="icon">用户</span>
                            </button>
                            <button class="zm-noti7-popup-tab-item thanks" role="tab">
                            <span class="icon">赞同和感谢</span>
                            </button>
                            </div>
                        </div>
                        <div class="zm-noti7-frame-border top"></div>
                        <div class="zm-noti7-frame">
                            <div class="zm-noti7-content message zh-scroller" style="position: relative; overflow: hidden;">
                                <div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">
                                <div class="zm-noti7-content-inner">
                                <div class="zm-noti7-content-body">
                                <div class="zm-noti7-popup-loading">
                                <span class="noti-spinner-loading"></span>
                                </div>
                                </div>
                                </div>
                                </div></div>
                                <div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div>
                            </div>
                            <div class="zm-noti7-content user zh-scroller" style="display: none; position: relative; overflow: hidden;"><div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">
                            <div class="zm-noti7-content-inner">
                            <div class="zm-noti7-content-body">
                            <div class="zm-noti7-popup-loading">
                            <span class="noti-spinner-loading"></span>
                            </div>
                            </div>
                            </div>
                            </div></div><div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div></div>
                            <div class="zm-noti7-content thanks zh-scroller" style="display: none; position: relative; overflow: hidden;"><div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">
                            <div class="zm-noti7-content-inner">
                            <div class="zm-noti7-content-body">
                            <div class="zm-noti7-popup-loading">
                            <span class="noti-spinner-loading"></span>
                            </div>
                            </div>
                            </div>
                            </div></div><div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div></div>
                        </div>
                        <div class="zm-noti7-frame-border bottom"></div>
                        <div class="zm-noti7-popup-footer">
                            <a href="https://nowcoder.com/notifications" class="zm-noti7-popup-footer-all zg-right">查看全部 »</a>
                            <a href="https://nowcoder.com/settings/notification" class="zm-noti7-popup-footer-set" title="通知设置"><i class="zg-icon zg-icon-settings"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="zu-global-notify" id="zh-global-message" style="display:none">
        <div class="zg-wrap">
            <div class="zu-global-nitify-inner">
            <a class="zu-global-notify-close" href="javascript:;" title="关闭" name="close">x</a>
            <span class="zu-global-notify-icon"></span>
            <span class="zu-global-notify-msg"></span>
            </div>
        </div>
    </div>


    <div class="zu-global-notify" id="zh-global-message" style="display:none">
        <div class="zg-wrap">
            <div class="zu-global-nitify-inner">
                <a class="zu-global-notify-close" href="javascript:;" title="关闭" name="close">x</a>
                <span class="zu-global-notify-icon"></span>
                <span class="zu-global-notify-msg"></span>
            </div>
        </div>
    </div>
    <div class="zu-global-notify zu-global-notify-info" id="zh-question-redirect-info" style="display:none"></div>
    <div class="zu-global-notify zu-global-notify-info" id="zh-question-notification-summary" style="display:none;">
        <div class="zg-wrap">
            <div class="zu-global-nitify-inner">
                <span class="zu-global-notify-icon"></span>
                <ul class="zu-question-notify-wrap"></ul>
            </div>
        </div>
    </div>
    <div class="zg-wrap zu-main clearfix with-indention-votebar" itemscope="" itemtype="http://schema.org/Question" id="zh-single-question-page" data-urltoken="36301524" role="main">
        <div class="zu-main-content">
            <div class="zu-main-content-inner">
                <meta itemprop="isTopQuestion" content="false">
                <meta itemprop="visitsCount" content="402">

                <div id="zh-question-title" data-editable="true" class="zm-editable-status-normal">
                    <h2 class="zm-item-title">

                    <span class="zm-editable-content">${question.title}</span>

                    </h2>
                </div>
                <div id="zh-question-detail" class="zm-item-rich-text zm-editable-status-normal" data-resourceid="6727688" data-action="/question/detail">
                    <div class="zm-editable-content">${question.content}</div>
                </div>
                <div class="zm-side-section">
                    <div class="zm-side-section-inner" id="zh-question-side-header-wrap">
                        <button data-follow="q:m:button" class="follow-button zg-follow zg-btn-green" data-id="6727688">关注问题</button>
                        <div class="zg-btn-white goog-inline-block goog-menu-button" id="zh-question-operation-menu" role="button" aria-expanded="false" tabindex="0" aria-haspopup="true" style="-webkit-user-select: none;">
                            <div class="goog-inline-block goog-menu-button-outer-box">
                                <div class="goog-inline-block goog-menu-button-inner-box">
                                    <div class="goog-inline-block goog-menu-button-caption">
                                        <i class="zg-icon-dropdown-menu zg-icon"></i>
                                        <b class="hide-text">设置</b></div>
                                    <div class="goog-inline-block goog-menu-button-dropdown">&nbsp;</div></div>
                            </div>
                        </div>
                        <div class="goog-menu goog-menu-vertical" role="menu" aria-haspopup="true" style="-webkit-user-select: none; visibility: visible; left: 92px; top: 33px; display: none;">
                            <div class="goog-menuitem" role="menuitem" id=":8" style="-webkit-user-select: none;">
                                <div class="goog-menuitem-content" style="-webkit-user-select: none;">使用匿名身份</div></div>
                            <div class="goog-menuitem" role="menuitem" id=":9" style="-webkit-user-select: none;">
                                <div class="goog-menuitem-content" style="-webkit-user-select: none;">问题重定向</div></div>
                        </div>
                        <div class="zh-question-followers-sidebar">
                            <div class="zg-gray-normal">
                                <a href="">
                                    <strong>9</strong></a>人关注该问题</div>
                            <div class="list zu-small-avatar-list zg-clear">
                                <span class="zm-item-link-avatar">
                                    <img title="匿名用户" class="zm-item-img-avatar" src="../images/res/aadd7b895_s.jpg"></span>
                                <a data-tip="p$b$yi-yi-98-91-99" class="zm-item-link-avatar" href="" data-original_title="奕奕">
                                    <img src="../images/res/6ceea810748d179f57cac0baa5cf9592_s.jpg" class="zm-item-img-avatar"></a>
                                <a data-tip="p$b$wang-wu-29-54" class="zm-item-link-avatar" href="" data-original_title="王五">
                                    <img src="../images/res/da8e974dc_s.jpg" class="zm-item-img-avatar"></a>
                                <a data-tip="p$b$huang-xiang-90-99" class="zm-item-link-avatar" href="" data-original_title="案小声">
                                    <img src="../images/res/19456ebfe8b207320735f282769ac635_s.jpg" class="zm-item-img-avatar"></a>
                                <a data-tip="p$b$yingxiaodao" class="zm-item-link-avatar" href="" data-original_title="营销岛">
                                    <img src="../images/res/11be4a90ed938abfbab4899df56ee754_s.png" class="zm-item-img-avatar"></a>
                                <a data-tip="p$b$hero-hou-23" class="zm-item-link-avatar" href="" data-original_title="Hero Hou">
                                    <img src="../images/res/da8e974dc_s.jpg" class="zm-item-img-avatar"></a>
                                <a data-tip="p$b$jin-hua-zhang-10" class="zm-item-link-avatar" href="" data-original_title="荷尔蒙毒品">
                                    <img src="../images/res/d207854fffc9e0289fbd6bbbb3986988_s.jpg" class="zm-item-img-avatar"></a>
                                <a title="宇轩" data-tip="p$t$yu-xuan-91-11" class="zm-item-link-avatar" href="">
                                    <img src="../images/res/1ac7840eeb19ada0bbf85f51702d5784_s.jpg" class="zm-item-img-avatar"></a>
                                <a title="尹锋" data-tip="p$t$yin-feng" class="zm-item-link-avatar" href="">
                                    <img src="../images/res/da8e974dc_s.jpg" class="zm-item-img-avatar"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="zh-question-answer-wrap" data-pagesize="10" class="zh-question-answer-wrapper navigable" data-widget="navigable" data-navigable-options="{&quot;items&quot;: &quot;&gt;.zm-item-answer&quot;}" data-init="{&quot;params&quot;: {&quot;url_token&quot;: 36301524, &quot;pagesize&quot;: 10, &quot;offset&quot;: 0}, &quot;nodename&quot;: &quot;QuestionAnswerListV2&quot;}">
                    <#list comments as comment>
                    <div tabindex="-1" class="zm-item-answer  zm-item-expanded" itemprop="topAnswer" itemscope="" itemtype="http://schema.org/Answer" data-aid="22162611" data-atoken="66862039" data-collapsed="0" data-created="1444310527" data-deleted="0" data-helpful="1" data-isowner="0" data-copyable="1" data-za-module="AnswerItem">
                        <link itemprop="url" href="">
                        <meta itemprop="answer-id" content="22162611">
                        <meta itemprop="answer-url-token" content="66862039">
                        <a class="zg-anchor-hidden" name="answer-22162611"></a>
                        <div class="zm-votebar goog-scrollfloater" data-za-module="VoteBar">
                            <button class="up" aria-pressed="false" title="赞同">
                                <i class="icon vote-arrow"></i>
                                <span class="count">28</span>
                                <span class="label sr-only">赞同</span></button>
                            <button class="down" aria-pressed="false" title="反对，不会显示你的姓名">
                                <i class="icon vote-arrow"></i>
                                <span class="label sr-only">反对，不会显示你的姓名</span></button>
                        </div>
                        <div class="answer-head">
                            <div class="zm-item-answer-author-info">
                                <a class="zm-item-link-avatar avatar-link" href="" target="_blank" data-tip="p$t$yingxiaodao">
                                    <img src="${comment.user.headUrl}" class="zm-list-avatar avatar"></a>
                                <a class="author-link" data-tip="p$t$yingxiaodao" target="_blank" href="">${comment.user.name}</a>
                                </div>
                            <div class="zm-item-vote-info" data-votecount="28" data-za-module="VoteInfo">
                                <span class="voters text">
                                    <a href="" class="more text">
                                        <span class="js-voteCount">28</span>&nbsp;人赞同</a></span>
                            </div>
                        </div>
                        <div class="zm-item-rich-text expandable js-collapse-body" data-resourceid="6727688" data-action="/answer/content" data-author-name="营销岛" data-entry-url="/question/36301524/answer/66862039">

                            <div class="zm-editable-content clearfix">
                                ${comment.comment.content}
                            </div>
                        </div>
                        <a class="zg-anchor-hidden ac" name="22162611-comment"></a>
                        <div class="zm-item-meta answer-actions clearfix js-contentActions">
                            <div class="zm-meta-panel">
                                <a itemprop="url" class="answer-date-link meta-item" target="_blank" href="">发布于 ${(comment.comment.createdDate?string("yyyy-MM-dd hh:mm:ss"))!'无数据'}</a>
                                <a href="" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox">
                                    <i class="z-icon-comment"></i>${question.commentCount}条评论</a>
                                <a href="" class="meta-item zu-autohide js-thank" data-thanked="false">
                                    <i class="z-icon-thank"></i>感谢</a>

                                <button class="item-collapse js-collapse" style="transition: none;">
                                    <i class="z-icon-fold"></i>收起</button>
                            </div>
                        </div>
                    </div>
                    </#list>
                </div>
                <div id="zh-question-collapsed-link" class="zu-question-collap-title" style="display:none">
                    <a href="javascript:;" name="expand" class="zg-link-gray" id="zh-question-collapsed-switcher">
                        <span id="zh-question-collapsed-num">0</span>个回答被折叠</a>（
                    <a target="_blank" href="" class="zg-link-gray">为什么？</a>）</div>
                <div id="zh-question-collapsed-wrap" class="zh-question-answer-wrapper" style="display:none"></div>
                <a name="draft"></a>

                <form action="/addComment" method="post">
                    <input type="hidden" name="questionId" value="${question.id}"/>
                    <div id="zh-question-answer-form-wrap" class="zh-question-answer-form-wrap">
                        <div class="zm-editable-editor-wrap" style="">
                            <div class="zm-editable-editor-outer">
                                <div class="zm-editable-editor-field-wrap">
                                    <textarea name="content" id="content" class="zm-editable-editor-field-element editable" style="width:100%;"></textarea>
                                </div>
                            </div>

                            <div class="zm-command clearfix">
                            <span class=" zg-right">
                                <button type="submit" class="submit-button zg-btn-blue">发布回答</button></span>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <#include "js.ftl">
    <script type="text/javascript" src="/scripts/main/site/detail.js"></script>
<#include "footer.ftl">