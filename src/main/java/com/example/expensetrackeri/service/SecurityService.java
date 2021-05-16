package com.example.expensetrackeri.service;

public interface SecurityService {
	String findLoggedInUsername();

	void autoLogin(String username, String password);
}