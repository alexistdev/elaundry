<?php

namespace App\Http\Controllers\Api\User;

use App\Http\Controllers\Controller;
use App\Models\Store;
use App\Models\Transaksi;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class LaundryController extends Controller
{
    public function list_laundry()
    {

        $data = Store::select('id', 'nama_laundry', 'phone', 'alamat', 'longitude', 'latitude')->orderBy('id', 'DESC')->where('status', 1)->get();
        if (!$data->isEmpty()) {
            return response()->json([
                'status' => true,
                'message' => "berhasil!",
                'data' => $data
            ], 200);
        }
        return response()->json([
            'status' => false,
            'message' => "data kosong!",
        ], 404);

    }

    public function order(Request $request)
    {
        $rules = [
            'store_id' => 'required|numeric',
            'user_id' => 'required|numeric',
            'satuan' => 'required|numeric',
            'phone' => 'required|max:255',
        ];
        $validator = Validator::make($request->all(), $rules);
        if ($validator->fails()) {
            return response()->json([
                'status' => false,
                'message' => "data tidak lengkap",
            ], 404);
        } else {
            $cekUser = User::where('role_id',3)->find($request->user_id);
            if($cekUser!= null){
                $cekStore = Store::find($request->store_id);
                if($cekStore != null){
                    $cekTransaksi = Transaksi::where('user_id',$request->user_id)->where('status','<',3)->first();
                    if($cekTransaksi == null){
                        $transaksi = new Transaksi();
                        $transaksi->user_id = $request->user_id;
                        $transaksi->store_id = $request->store_id;
                        $transaksi->satuan = $request->satuan;
                        $transaksi->total = $request->satuan * $cekStore->harga_kiloan ?? 0;
                        $transaksi->phone = $request->phone;
                        $transaksi->save();

                        return response()->json([
                            'status' => true,
                            'message' => "data berhasil disimpan!",
                        ], 200);
                    }
                    return response()->json([
                        'status' => false,
                        'message' => "Anda masih memiliki pesanan yang belum selesai!",
                    ], 401);
                }
            }
            return response()->json([
                'status' => false,
                'message' => "data tidak ditemukan",
            ], 404);
        }
    }
}
