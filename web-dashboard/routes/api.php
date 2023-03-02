<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\LoginController as Auth;
use App\Http\Controllers\Api\User\LaundryController as UserLaundry;
use App\Http\Controllers\Api\Store\OrderController as StoreOrder;

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
Route::post('/v1/auth/akun', [Auth::class, 'setting_akun'])->name('api.akun');
Route::post('/v1/register', [Auth::class, 'register'])->name('api.register');
Route::post('/v1/recovery_password', [Auth::class, 'password_recovery'])->name('api.recovery');

/**
 * User API
 */
Route::get('/v1/list_laundry', [UserLaundry::class, 'list_laundry'])->name('api.laundry.list');
Route::post('/v1/order', [UserLaundry::class, 'order'])->name('api.laundry.order');
Route::get('/v1/history', [UserLaundry::class, 'history'])->name('api.laundry.history');

/**
 * Store API
 */
Route::get('/v2/order', [StoreOrder::class, 'get_order'])->name('api.store.order');
Route::post('/v2/order', [StoreOrder::class, 'ubah_status'])->name('api.store.status');
