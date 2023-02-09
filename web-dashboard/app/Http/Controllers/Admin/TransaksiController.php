<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Traits\AdminTrait;
use App\Models\Store;
use App\Models\Transaksi;
use Illuminate\Http\Request;
use Yajra\DataTables\DataTables;

class TransaksiController extends Controller
{
    use AdminTrait;

    public function index(Request $request)
    {

        if ($request->ajax()) {
            $transaksi = Transaksi::with('store','customer')->get();
//            $store = Store::orderBy('id','desc')->store->get();
            return DataTables::of($transaksi)
                ->addIndexColumn()
                ->editColumn('selesai', function ($request) {
                    return date("d-m-Y",strtotime($request->tgl_selesai));
                })
                ->editColumn('total', function ($request) {
                    return "Rp. " . number_format($request->total,0,".",".");
                })
                ->addColumn('action', function ($row) {
//                    $url = route('', base64_encode($row->id));
                    $url = "";
                    $btn = " <a href=\"$url\" class=\"btn btn-outline-primary px-5\"> Tolak</a>";
//                    $btn = $btn . " <a href=\"#\" class=\"btn btn-outline-danger open-hapus\" data-id=\"$row->id\" data-bs-toggle=\"modal\" data-bs-target=\"#hapusModal\"> Delete</i></a>";
                    $btn = $btn . " <a href=\"$url\" class=\"btn btn-outline-danger px-5\"> Hapus</a>";
                    return $btn;
                })
                ->rawColumns(['action'])
                ->make(true);
        }
        return view('admin.transaksi', array(
            'judul' => "Dashboard Guru | FavoriteIDN",
            'menuUtama' => 'dashboard',
            'menuKedua' => 'dashboard',
        ));
    }
}
