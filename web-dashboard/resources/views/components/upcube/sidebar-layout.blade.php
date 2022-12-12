<div>
    <div class="vertical-menu">

        <div data-simplebar class="h-100">

            <!-- User details -->
            <div class="user-profile text-center mt-3">
                <div class="">
                    <img src="{{asset('template/upcube/assets/images/users/avatar-1.jpg')}}" alt="" class="avatar-md rounded-circle">
                </div>
                <div class="mt-3">
                    <h4 class="font-size-16 mb-1">Administrator</h4>
                    <span class="text-muted"><i class="ri-record-circle-line align-middle font-size-14 text-success"></i> Online</span>
                </div>
            </div>

            <!--- Sidemenu -->
            <div id="sidebar-menu">
                <!-- Left Menu Start -->
                <ul class="metismenu list-unstyled" id="side-menu">
                    <li class="menu-title">Menu</li>
                    <li>
                        <a href="{{route('adm.dashboard')}}" class="waves-effect">
                            <i class="ri-dashboard-line"></i>
                            <span>Dashboard</span>
                        </a>
                    </li>

                    <li>
                        <a href="calendar.html" class=" waves-effect">
                            <i class="ri-calendar-2-line"></i>
                            <span>Transaksi</span>
                        </a>
                    </li>

                    <li class="menu-title">Master Data</li>

                    <li>
                        <a href="{{route('adm.dashboard')}}" class="waves-effect">
                            <i class="ri-store-line"></i>
                            <span>Laundry</span>
                        </a>
                    </li>
                    <li>
                        <a href="{{route('adm.customer')}}" class="waves-effect">
                            <i class="ri-dashboard-line"></i>
                            <span>Users</span>
                        </a>
                    </li>

                </ul>
            </div>
            <!-- Sidebar -->
        </div>
    </div>
</div>
