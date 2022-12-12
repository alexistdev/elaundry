<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\Http\Requests\Auth\LoginRequest;
use App\Providers\RouteServiceProvider;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class AuthenticatedSessionController extends Controller
{
    /**
     * Display the login view.
     *
     * @return \Illuminate\View\View
     */
    public function create()
    {
        return view('auth.login');
    }

    /**
     * Handle an incoming authentication request.
     *
     * @param  \App\Http\Requests\Auth\LoginRequest  $request
     * @return \Illuminate\Http\RedirectResponse
     */
    public function store(LoginRequest $request)
    {
        $request->authenticate();
        $request->session()->regenerate();
        /** mendapatkan role */
        $user = Auth::user();
        $roleId = (Int) $user->role_id;


        /** Cek Auth Role */
        switch ($roleId){
            case 1:
                return redirect()->intended(RouteServiceProvider::ADMIN);
            case 2:
                return redirect()->intended(RouteServiceProvider::STAFF);
            case 3:
                return redirect()->intended(RouteServiceProvider::USER);
            default:
                $request->session()->invalidate();
                $request->session()->regenerateToken();
                abort('404','NOT FOUND');
        }
    }

    /**
     * Destroy an authenticated session.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\RedirectResponse
     */
    public function destroy(Request $request)
    {
        Auth::guard('web')->logout();

        $request->session()->invalidate();

        $request->session()->regenerateToken();

        return redirect('/');
    }
}
