package com.citybank.util;

public final class UrlConstraint {
	private UrlConstraint() {
	}

	private static final String VERSION = "/v1";
	private static final String API = "/api";

	public static class ProductManagement {
		public static final String ROOT = API + VERSION + "/products";
		public static final String CREATE = "/create";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";
		public static final String GET = "/{id}";
		public static final String GET_ALL = "/all";
	}

	public static class AuthManagement {
		public static final String ROOT = API + "/auth";
		public static final String LOGIN = "/login";
		public static final String CURRENT_USER = "/current_user";
		public static final String DOWNLOAD = "download";
	}

	public static class UserManagement {
		public static final String ROOT = API + "/user";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class BranchManagement {
		public static final String ROOT = API + "/branch";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class CustomerManagement {
		public static final String ROOT = API + "/customer";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class AccountManagement {
		public static final String ROOT = API + "/account";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String GET_BY_ACNO = "/acno/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class DepositManagement {
		public static final String ROOT = API + "/deposit";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class WithdrawManagement {
		public static final String ROOT = API + "/withdraw";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class TransferManagement {
		public static final String ROOT = API + "/transfer";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}

	public static class AccountInfoManagement {
		public static final String ROOT = API + "/accountInfo";
		public static final String CREATE = "/create";
		public static final String GET_All = "/all";
		public static final String GET = "/{id}";
		public static final String UPDATE = "/{id}";
		public static final String DELETE = "/{id}";

	}
}
