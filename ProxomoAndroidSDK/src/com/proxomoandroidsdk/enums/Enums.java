package com.proxomoandroidsdk.enums;

import com.proxomoandroidsdk.helpers.EnumAbstract;

public class Enums {
	public enum CommunicationType {
		XML, JSON
	}

	public enum EventPrivacy implements EnumAbstract {
		Secret(0), Closed(1), Open(2);
		private int value;

		private EventPrivacy(int value) {
			this.value = value;
		}

		@Override
		public int convert() {
			return this.value;
		}

	}

	public enum EventStatus implements EnumAbstract {
		Upcoming(0), Complete(1), Canceled(2);
		private int value;

		private EventStatus(int value) {
			this.value = value;
		}

		public int convert() {
			return value;
		}
	}

	public enum EventParticipantStatus implements EnumAbstract {
		NoReply(0), Maybe(1), Invited(2), Attending(3), Declined(4), RequestInvitation(
				5), RequestDeclined(6);
		private int value;

		private EventParticipantStatus(int value) {
			this.value = value;
		}

		public int convert() {
			return value;
		}
	}

	public enum TaskResult implements EnumAbstract {
		Error(0), Success(1);
		private int value;

		private TaskResult(int value) {
			this.value = value;
		}

		public int convert() {
			return value;
		}
	}

	public enum TaskMethod implements EnumAbstract {
		URL(0);
		private int value;

		private TaskMethod(int value) {
			this.value = value;
		}

		public int convert() {
			return value;
		}
	}

	public enum TaskType implements EnumAbstract {
		Once(0), Reoccurring(1);
		private int value;

		private TaskType(int value) {
			this.value = value;
		}

		public int convert() {
			return this.value;
		}
	}

	public enum USTimeZone implements EnumAbstract {
		EasterTime(0), CentralTime(1), MountainTime(2), MountainTimeArizona(3), PacificTime(
				4), AlaskanTime(5), HawaiianTime(6), SamoaTime(7);
		// this enum needs to be reviewed as it is not the same with the c#
		// version
		private int value;

		private USTimeZone(int value) {
			this.value = value;
		}

		public int convert() {
			return value;
		}
	}

	public enum VerificationStatus implements EnumAbstract {
		None(0), Sent(1), Complete(2), Error(3);
		private int value;

		private VerificationStatus(int value) {
			this.value = value;
		}

		public int convert() {
			return this.value;
		}
	}

	public enum LocationSecurity implements EnumAbstract {
		Open(0), Private(1);
		private int value;

		private LocationSecurity(int value) {
			this.value = value;
		}

		public int convert() {
			return this.value;
		}
	}

	public enum LocationSearchScope implements EnumAbstract {
		All(0), GlobalOnly(1), ApplicationOnly(2);
		private int value;

		private LocationSearchScope(int value) {
			this.value = value;
		}

		public int convert() {
			return this.value;
		}
	}
	public enum FriendStatus implements EnumAbstract{
		None(0),InvitationSent(1),Approved(2),Ignored(3),IncomingInvitation(4),InvitationIgnored(5);
		private int value;
		private FriendStatus(int value){
			this.value=value;
		}
		public int convert(){
			return this.value;
		}
	}
	public enum SocialNetwork implements EnumAbstract{
		Facebook(0),Twitter(1);
		private int value;
		private SocialNetwork(int value){
			this.value=value;
		}
		public int convert(){
			return this.value;
		}
	}
	public enum FriendResponse implements EnumAbstract{
		Ignore(0),Accept(1),Cancel(2);
		private int value;
		private FriendResponse(int value){
			this.value=value;
		}
		public int convert(){
			return this.value;
		}
	}
}
