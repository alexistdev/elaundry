<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\User;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Str;

class LoginController extends Controller
{
    public function auth_login(Request $request)
    {
        $rules = [
            'email' => 'required|email|max:255',
            'password' => 'required|max:255',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => "data tidak lengkap",
            ], 404);
        } else {
            $data = [
                'email' => $request->input('email'),
                'password' => $request->input('password'),
            ];

            Auth::attempt($data);
            if (Auth::check()) {
                return response()->json([
                    'status' => true,
                    'message' => "Berhasil",
                    'user_id' => Auth::user()->id,
                    'role' => Auth::user()->role_id,
                ], 200);

            } else {
                return response()->json([
                    'status' => false,
                    'message' => "Username atau email salah!",
                ], 401);
            }
        }
    }

    public function register(Request $request)
    {
        $rules = [
            'name' => 'required|max:255',
            'phone' => 'required|max:255',
            'email' => 'required|email|unique:users,email|max:255',
            'password' => 'required|max:255',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => $validator->messages()->first(),
            ], 404);
        } else {
            if ($request->routeIs('api.*')) {
                DB::beginTransaction();
                try {
                    $user = new User();
                    $user->role_id = 3;
                    $user->name = $request->name;
                    $user->email = $request->email;
                    $user->password = Hash::make($request->password);
                    $user->status = 1;
                    $user->save();
                    DB::commit();
                    return response()->json([
                        'status' => true,
                        'message' => "Akun berhasil dibuat",
                    ], 200);
                } catch (Exception $e) {
                    DB::rollback();
                    return response()->json([
                        'status' => false,
                        'message' => $e->getMessage()
                    ], 500);
                }
            } else {
                return response()->json([
                    'status' => false,
                    'message' => "NOT AUTHORIZED",
                ], 401);
            }
        }
    }
}
