<x-upcube.admin-template>
    @push('customCSS')
        <!-- DataTables -->
        <link href="{{asset('template/upcube/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css')}}" rel="stylesheet" type="text/css" />
        <link href="{{asset('template/upcube/assets/libs/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css')}}" rel="stylesheet" type="text/css" />
        <link href="{{asset('template/upcube/assets/libs/datatables.net-select-bs4/css//select.bootstrap4.min.css')}}" rel="stylesheet" type="text/css" />

        <!-- Responsive datatable examples -->
        <link href="{{asset('template/upcube/assets/libs/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css')}}" rel="stylesheet" type="text/css" />
    @endpush
    <!-- start page title -->
    <div class="row">
        <div class="col-12">
            <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                <h4 class="mb-sm-0">Data Transaksi</h4>

                <div class="page-title-right">
                    <ol class="breadcrumb m-0">
                        <li class="breadcrumb-item"><a href="javascript: void(0);">Dashboard</a></li>
                        <li class="breadcrumb-item active">Laundry</li>
                    </ol>
                </div>

            </div>
        </div>
    </div>
    <!-- end page title -->


    <div class="row">
        <div class="col-xl-12">
            <div class="card">
                <div class="card-body">

                   <div class="row mt-3">
                       <div class="col-md-12">
                           <div class="table-responsive">
                               <table id="tabelTransaksi" class="table table-striped table-bordered" style="width:100%">
                                   <thead class="table-light">
                                   <tr>
                                       <th class="text-center">No.</th>
                                       <th class="text-center">Store</th>
                                       <th class="text-center">Customer</th>
                                       <th class="text-center">Tanggal Selesai</th>
                                       <th class="text-center">Total</th>
                                       <th class="text-center">Status</th>
                                       <th class="text-center">Action</th>
                                   </tr>
                                   </thead><!-- end thead -->
                                   <tbody>
                                   </tbody>
                               </table> <!-- end table -->
                           </div>
                       </div>
                   </div>


                </div><!-- end card -->
            </div><!-- end card -->
        </div>
        <!-- end col -->
    </div>
    <!-- end row -->
    @push('customJS')

            <!-- Required datatable js -->
            <script src="{{asset('template/upcube/assets/libs/datatables.net/js/jquery.dataTables.min.js')}}"></script>
            <script src="{{asset('template/upcube/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js')}}"></script>
            <!-- Responsive examples -->
            <script src="{{asset('template/upcube/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js')}}"></script>
            <script src="{{asset('template/upcube/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js')}}"></script>
            <script>
                $(document).ready(function () {
                    let base_url = '{{route('admin.transaksi')}}';

{{--                    @if($errors->hasbag('tambah'))--}}
{{--                    openModal($('#modalTambah'));--}}
{{--                    @endif--}}

{{--                    $('.modal').on('hidden.bs.modal', function (e) {--}}
{{--                        e.preventDefault();--}}
{{--                        let pesanError = $('.errorMessage');--}}
{{--                        let errorInput = $('.errorInput');--}}
{{--                        let errorLabel = $('.errorLabel');--}}
{{--                        pesanError.html("");--}}
{{--                        errorInput.removeClass('is-invalid');--}}
{{--                        errorLabel.removeClass('text-danger');--}}
{{--                    })--}}
//                     $('#tabelCustomer').DataTable();

                    $('#tabelTransaksi').DataTable({
                        responsive: true,
                        processing: true,
                        serverSide: true,
                        ajax: {
                            type: 'GET',
                            url: base_url,
                            async: true,
                            dataType: 'json',
                        },
                        columns: [
                            {
                                data: 'index',
                                class: 'text-center',
                                defaultContent: '',
                                orderable: false,
                                searchable: false,
                                width: '5%',
                                render: function (data, type, row, meta) {
                                    return meta.row + meta.settings._iDisplayStart + 1; //auto increment
                                }
                            },
                            {data: 'store.nama_laundry', class: 'text-left'},
                            {data: 'customer.name', class: 'text-left'},
                            {data: 'selesai', class: 'text-center'},
                            {data: 'total', class: 'text-center'},
                            {data: 'status', class: 'text-center'},
                            {data: 'action', class: 'text-center', width: '25%', orderable: false},
                        ],
                        "bDestroy": true
                    });
                });
            </script>
    @endpush
</x-upcube.admin-template>
