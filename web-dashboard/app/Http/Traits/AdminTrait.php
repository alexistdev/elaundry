<?php

namespace App\Http\Traits;

use Illuminate\Support\Facades\Auth;

trait AdminTrait
{
    protected $users;
    protected $namaApps;

    public function __construct()
    {
        $this->middleware(function ($request, $next) {
            $this->users=Auth::user();
            $this->namaApps = config('app.name');
            return $next($request);
        });
    }
}
