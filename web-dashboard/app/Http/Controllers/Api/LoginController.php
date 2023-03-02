<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Customer;
use App\Models\Store;
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
    public function get_akun(Request $request)
    {
        $rules = [
            'user_id' => 'required|numeric',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => "data tidak lengkap",
            ], 404);
        } else {
            $user=User::where('role_id','!=',"1")->find($request->user_id);
            if($user->role_id != "2"){
                $customer = Customer::where('user_id',$user->id)->first();
                return response()->json([
                    'status' => true,
                    'message' => "berhasil",
                    'data' => $user,
                ], 200);
            } else {
                $store= Store::where('user_id',$user->id)->first();
                return response()->json([
                    'status' => true,
                    'message' => "berhasil",
                    'nama_laundry' => $store->nama_laundry,
                    'phone' => $store->phone,
                    'alamat' => $store->alamat,
                    'latitude' => $store->latitude,
                    'longitude' => $store->longitude,
                ], 200);
            }
        }
    }
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
                $role = Auth::user()->role_id;
                if($role != "3"){
                    $laundry = Store::where('user_id',Auth::user()->id)->first();
                    return response()->json([
                        'status' => true,
                        'message' => "Berhasil",
                        'user_id' => Auth::user()->id,
                        'role' => Auth::user()->role_id,
                        'latitude' => $laundry->latitude,
                        'longitude' => $laundry->longitude,
                    ], 200);
                } else {
                    $customer = Customer::where('user_id',Auth::user()->id)->first();
                    return response()->json([
                        'status' => true,
                        'message' => "Berhasil",
                        'user_id' => Auth::user()->id,
                        'role' => Auth::user()->role_id,
                        'latitude' => $customer->latitude,
                        'longitude' => $customer->longitude,
                    ], 200);
                }


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

    public function password_recovery(Request $request)
    {
        $rules = [
            'email' => 'required|email',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => $validator->messages()->first(),
            ], 404);
        } else {
            $cekIfExists = User::where('email',$request->email)->first();

            if($cekIfExists != null){
                $password = rand(10000,1000000);
                $cekIfExists->update([
                   'password' =>  Hash::make($password),
                ]);
                //todo sent email
                return response()->json([
                    'status' => true,
                    'message' => "Success",
                    'data' => $password
                ], 200);
            }
            return response()->json([
                'status' => false,
                'message' => "Email Not Found",
            ], 401);
        }
    }

    public function setting_akun(Request $request)
    {
        $rules = [
            'user_id' => 'required|numeric',
            'password' => 'required|max:255',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => "data tidak lengkap",
            ], 404);
        } else {
            $user = User::find($request->user_id);
            if($user !== null){
                $user->update([
                    'password' => Hash::make($request->password),
                ]);
                return response()->json([
                    'status' => true,
                    'message' => "data berhasil",
                ], 200);
            } else {
                return response()->json([
                    'status' => false,
                    'message' => "kosong",
                ], 404);
            }
        }
    }
}
