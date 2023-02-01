<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\LoginController as Auth;
use App\Http\Controllers\Api\User\LaundryController as UserLaundry;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/v1/auth', [Auth::class, 'Auth_login'])->name('api.login');
Route::post('/v1/register', [Auth::class, 'register'])->name('api.register');
Route::post('/v1/recovery_password', [Auth::class, 'password_recovery'])->name('api.recovery');

Route::get('/v1/list_laundry', [UserLaundry::class, 'list_laundry'])->name('api.laundry.list');
