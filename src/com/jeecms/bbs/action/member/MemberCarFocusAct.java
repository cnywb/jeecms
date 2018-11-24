/**
 * 
 */
package com.jeecms.bbs.action.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jeecms.bbs.manager.member.BbsMemberCarFocusMng;

/**
 * @author xuw
 *
 */
@Controller
public class MemberCarFocusAct {
	
	
	private  final Logger  logger= LoggerFactory.getLogger(super.getClass());
	
	@Autowired
	private BbsMemberCarFocusMng bbsMemberCarFocusMng;
	
	
}
