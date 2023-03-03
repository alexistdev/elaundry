<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Requests\LaundryRequest;
use App\Http\Traits\AdminTrait;
use App\Models\Store;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Yajra\DataTables\DataTables;
use Exception;

class LaundryController extends Controller
{
    use AdminTrait;

    public function index(Request $request)
    {
//        return  $store = Store::all();
        if ($request->ajax()) {
            $store = Store::all();
//            $store = Store::orderBy('id','desc')->store->get();
            return DataTables::of($store)
                ->addIndexColumn()
//                ->editColumn('created_at', function ($request) {
//                    return $request->created_at->format('d-m-Y H:i:s');
//                })
//                ->editColumn('name', function ($request) {
//                    return ucwords($request->pelanggan?->name);
//                })
                ->addColumn('action', function ($row) {
//                    $url = route('', base64_encode($row->id));
                    $url = "";
//                    $btn = " <a href=\"$url\" class=\"btn btn-outline-primary\"> Edit</a>";
//                    $btn = $btn . " <a href=\"#\" class=\"btn btn-danger btn-sm ml-auto open-hapus\" data-id=\"$row->id\" data-bs-toggle=\"modal\" data-bs-target=\"#hapusModal\"><i class=\"fas fa-trash\"></i> Delete</i></a>";
                    $btn = " <a href=\"$url\" class=\"btn btn-outline-danger open-hapus\" data-id=\"$row->user_id\" data-bs-toggle=\"modal\" data-bs-target=\"#modalHapus\"> Hapus</a>";
                    return $btn;
                })
                ->rawColumns(['action'])
                ->make(true);
        }
        return view('admin.laundry', array(
            'judul' => "Dashboard Guru | FavoriteIDN",
            'menuUtama' => 'dashboard',
            'menuKedua' => 'dashboard',
        ));
    }

    public function store(LaundryRequest $request)
    {
        $request->validated();
        DB::beginTransaction();
        try {
            //input user
            $user = new User();
            $user->role_id = 2;
            $user->email = $request->emailPengguna;
            $user->name = $request->namaPengguna;
            $user->password = Hash::make('1234');
            $user->status = 1;
            $user->save();
            $idUser = $user->id;
            //input launry
            $store = new Store();
            $store->user_id = $idUser;
            $store->nama_laundry = $request->namaLaundry;
            $store->phone = $request->phone;
            $store->alamat = $request->alamat;
            $store->longitude = '105.24516';
            $store->latitude = '-5.3774025';
            $store->harga_kiloan = 5000;
            $store->hari = 1;
            $store->status = 1;
            $store->save();
            DB::commit();
            return redirect(route('adm.laundry'))->with(['success' => "Data laundry berhasil ditambahakn!"]);
        } catch (Exception $e) {
            DB::rollback();
            return redirect(route('adm.laundry'))->withErrors(['warning' => $e->getMessage()]);
        }

    }

    public function destroy(Request $request)
    {
        if ($request->routeIs('adm.*')) {
            $rules = [
                'user_id' => 'required|numeric',
            ];
            $message = [
                'user_id.required' => "ID tidak ditemukan silahkan refresh halaman atau relogin!",
                'user_id.numeric' => "ID tidak ditemukan silahkan refresh halaman atau relogin!",
            ];
            $request->validate($rules, $message);
            $user= User::findOrFail($request->user_id);
            $user->delete();
            if ($request->routeIs('adm.customer.delete')) {
                return redirect(route('adm.customer'))->with(['warning' => "Data berhasil dihapus!"]);
            } else {
                return redirect(route('adm.laundry'))->with(['warning' => "Data berhasil dihapus!"]);
            }


        } else {
            return abort("404", "NOT FOUND");
        }
    }
}
