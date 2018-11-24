package com.jeecms.cms.entity.main;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
import com.jeecms.cms.entity.main.base.BaseCmsDepartment;
import com.jeecms.common.hibernate3.PriorityInterface;



public class CmsDepartment extends BaseCmsDepartment  implements PriorityInterface {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsDepartment () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsDepartment (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsDepartment (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Integer weights) {

		super (
			id,
			name,
			priority,
			weights);
	}

/*[CONSTRUCTOR MARKER END]*/

	public Integer[] getChannelIds() {
		Set<Channel> channels = getChannels();
		return Channel.fetchIds(channels);
	}
	public Integer[] getGuestBookCtgIds() {
		Set<CmsGuestbookCtg> channels = getGuestBookCtgs();
		return CmsGuestbookCtg.fetchIds(channels);
	}
	public Set<Integer> getChannelIds(Integer siteId) {
		Set<Channel> channels = getChannels();
		Set<Integer> ids = new HashSet<Integer>();
		for (Channel c : channels) {
			if (c.getSite().getId().equals(siteId)) {
				ids.add(c.getId());
			}
		}
		return ids;
	}
	public void addToChannels(Channel channel) {
		if (channel == null) {
			return;
		}
		Set<Channel> set = getChannels();
		if (set == null) {
			set = new HashSet<Channel>();
			setChannels(set);
		}
		set.add(channel);
	}
	public void addToGuestBookCtgs(CmsGuestbookCtg ctg) {
		if (ctg == null) {
			return;
		}
		Set<CmsGuestbookCtg> set =getGuestBookCtgs();
		if (set == null) {
			set = new HashSet<CmsGuestbookCtg>();
			setGuestBookCtgs(set);
		}
		set.add(ctg);
	}
	
	public static Integer[] fetchIds(Collection<CmsDepartment> departments) {
		if (departments == null) {
			return null;
		}
		Integer[] ids = new Integer[departments.size()];
		int i = 0;
		for (CmsDepartment r : departments) {
			ids[i++] = r.getId();
		}
		return ids;
	}

}